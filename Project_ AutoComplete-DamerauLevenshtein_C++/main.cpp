#include <vector>
#include <fstream>
#include <conio.h>
#include <iostream>

using std::string;
using std::vector;
using std::ifstream;
using std::ofstream;
using std::min;
using std::cout;
using std::endl;

string DICT_FILE = "input_en.txt";

// usage of struct
struct _term {
	string _name;
	signed _dist;

	bool operator <(const _term& _comp){
		if (_dist == _comp._dist) {
			if (_name.length() == _comp._name.length()) {
				return _name < _comp._name;
			}
			return _name.length() < _comp._name.length();
		}
		return _dist < _comp._dist;
	}
};

vector <_term> _dictionary;
char _char;
signed _dictionary_size;
string _word = "";
vector<string> _sentence;
vector<string> _new_words;



void _join(vector <_term>& _vect, signed _l, signed _l_m, signed _r_m, signed _r) {

	signed _temp_l = _l, _temp_r = _r_m, _i = 0;
	vector <_term> _temp(_r - _l + 1);

	while (_temp_l <= _l_m || _temp_r <= _r) {
		if (_temp_l > _l_m)
			_temp[_i++] = _vect[_temp_r++];
		else
			if (_temp_r > _r)
				_temp[_i++] = _vect[_temp_l++];
			else {
				if (_vect[_temp_l] < _vect[_temp_r])
					_temp[_i++] = _vect[_temp_l++];
				else
					_temp[_i++] = _vect[_temp_r++];
			}
	}

	for (signed _i = 0; _i < _r - _l + 1; ++_i)
		_vect[_l + _i] = _temp[_i];
	return;
}

// usage of sort, function, recursive function
void _sort(vector <_term>& _vect, signed _l, signed _r) {

	if (_l >= _r)
		return;

	signed _m = _l + ((_r - _l) >> 1);
	_sort(_vect, _l, _m);
	_sort(_vect, _m + 1, _r);
	_join(_vect, _l, _m, _m + 1, _r);

	return;
}

signed _leve_dist(string _a, string _b) {

	signed _len_a = _a.length(), _len_b = _b.length();
	vector < vector <signed> > _dist(2, vector <signed>(_len_a + 1, 0));

	for (signed _i = 0; _i <= _len_a; ++_i)
		_dist[0][_i] = _i;

	for (signed _i = 1; _i <= _len_b; ++_i)
		for (signed _j = 0; _j <= _len_a; ++_j) {
			if (_j == 0)
				_dist[_i % 2][_j] = _i;
			else
				if (_a[_j - 1] == _b[_i - 1])
					_dist[_i % 2][_j] = _dist[(_i - 1) % 2][_j - 1];
				else
					_dist[_i % 2][_j] = 1 + min(min(_dist[(_i - 1) % 2][_j], _dist[_i % 2][_j - 1]), _dist[(_i - 1) % 2][_j - 1]);
		}
	return _dist[_len_b % 2][_len_a];
}

void _load() {
	ifstream _load(DICT_FILE);
	_term _temp_term = { "", 0 };
	while (!_load.eof()) {
		_load >> _temp_term._name;
		_dictionary.push_back(_temp_term);
	}
	_dictionary_size = _dictionary.size();
}

void _save() {
	ofstream _save(DICT_FILE, ofstream::app);
	for (const auto& _word : _new_words)
		_save << endl << _word;
	_new_words.clear();
}

// usage of search
void _add_word() {
	bool exists = false;
	for (const auto& w : _dictionary) {
		if (w._name == _word || _word == "") {
			exists = true;
			break;
		}
	}

	if (!exists) {
		_new_words.push_back(_word);
		_dictionary.push_back({ _word, 0 });
		_dictionary_size++;
	}
	_sentence.push_back(_word);
	_word = "";
}

bool _parse() {
	system("cls");
	if (_char == 8) {
		if (_word.length())
			_word.pop_back();
		else if (_sentence.size()) {
			_word = _sentence.back();
			_sentence.pop_back();
		}
	}
	else if (_char == ' ') {
		_sentence.push_back(_word);
		_word = "";
	}
	else if (_char > '0' && _char < '5') {
		_sentence.push_back(_dictionary[_char - '1']._name);
		_word = "";
	}
	else if (_char == '9') {
		_add_word();
	}
	else if (_char == '0') {
		_save();
	}
	else if (!((_char < 'A') || (_char > 'Z' && _char < 'a') || (_char > 'z'))) {
		if (_char >= 'A' && _char <= 'Z')
			_char += 32;
		_word.push_back(_char);
	}
	else return false;
	return true;
}

void _print() {
	for (const auto& _s : _sentence)
		cout << _s << ' ';
	cout << _word << endl;

	// usage of array
	string _top[4] = { "", "", "", "" };


	for (signed _i = 0, _j = 0; _i < _dictionary_size && _j < 4; ++_i) {
		if (_dictionary[_i]._dist > 5)
			break;
		if (++_j > 5)
			break;
		_top[_i] = _dictionary[_i]._name;
	}

	for (signed _i = 0; _i < 4; ++_i) {
		if (_top[_i] != "")
			cout << _i + 1 << ". " << _top[_i] << ' ';
	}
	cout << "\n\nPress 9 to add '" << _word << "' to the dictionary";
	cout << "\nPress 0 to save the current dictionary into '" << DICT_FILE << "' file";
}



signed main() {
	_load();

	while (_char = getch()) {
		if (!_parse())
			break;

		for (signed _i = 0, _word_size = _word.length(); _i < _dictionary_size; ++_i)
			_dictionary[_i]._dist = _leve_dist(_word, _dictionary[_i]._name.substr(0, _word_size));

		_sort(_dictionary, 0, _dictionary_size - 1);
		_print();

		//for (; _dictionary[_dictionary_size - 1]._dist > 5; --_dictionary_size)
		//	_dictionary.pop_back();

	}
	cout << "non alphabetic character was omitted, program will be terminated..." << endl;

	return 0;
}

