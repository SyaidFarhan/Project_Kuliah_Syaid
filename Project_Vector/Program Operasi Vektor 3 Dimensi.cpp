#include <bits/stdc++.h>
using namespace std;
 
double norm(double arr1[]){
	double n=0, ans;
	for (int i=0;i<3;i++){
		n += arr1[i]*arr1[i];
	}
	ans = sqrt(n);
	return ans;
}
 
void sum(double arr1[],double arr2[]){
	double arr3[3];
	cout << "Hasil Penjumlahan Kedua Vektor adalah : (" ;
	for(int i=0; i<3;i++){
		arr3[i] = arr1[i]+arr2[i];
		cout << arr3[i] ;
		if(i<2){
			cout << ", ";
		} 
	}
	cout << ")";
	cout << endl;
}
 
double dot(double arr1[], double arr2[]){
	double ans=0;
	for(int i=0;i<3;i++){
		ans += arr1[i]*arr2[i];
	}
	return ans;
}

void angle(double arr1[], double arr2[]){
	double angle;
	angle = acos(dot(arr1, arr2)/(norm(arr1)*norm(arr2))) * 180/M_PI;
	cout << "Sudut antara Kedua Vektor adalah : " << angle << " derajat" << endl; 
}

void proyeksi(double arr1[], double arr2[]){
    double arr3[3];
    
    for (int i = 0; i < 3; i++){
        arr3[i] = (dot(arr1, arr2)/(pow(norm(arr2), 2))) * arr2[i];
    }
    
    cout << "(";
    for (int i = 0; i < 3; i++){
        cout << arr3[i];
        if(i<2){
			cout << ", ";
		}
    }
    cout << ")";
}

void crossProduct(double arr1[], double arr2[]){
	double arr3[3];
	arr3[0]=arr1[1]*arr2[2]-arr1[2]*arr2[1];
	arr3[1]=-(arr1[0]*arr2[2]-arr1[2]*arr2[0]);
	arr3[2]=arr1[0]*arr2[1]-arr1[1]*arr2[0];
	cout << "Cross Product antara Dua Vektor Tersebut adalah : (";
	for(int i=0;i<3;i++){
		cout << arr3[i] ;
		if(i<2){
			cout << ", ";
		}
	}
	cout << ")" << endl;
}
 
int main(){
	double arr1[3], arr2[3];
	
	cout << "--------------------------------" << endl << "Program Operasi Vektor 3 Dimensi" << endl;
	cout << "--------------------------------" << endl;
	
	cout << "Contoh Masukan = Vektor A : 1 2 3" << "\n\n";
	
	cout << "Masukkan Vektor A : ";
	for(int i=0;i<3;i++){
		cin >> arr1[i];
	}
	cout << "Masukkan Vektor B : ";
	for(int i=0;i<3;i++){
		cin >> arr2[i];
	}
	
	cout << fixed << setprecision(2);
	
	cout << endl << "=== Norm/Panjang Vektor ===" << endl;
	cout << "Norm/Panjang Vektor A adalah : "<<	norm(arr1) <<endl;
	cout << "Norm/Panjang Vektor B adalah : "<<	norm(arr2) <<endl;
	
	cout << endl << "=== Penjumlahan Kedua Vektor ===" << endl;
	sum(arr1, arr2);
	
	cout << endl << "=== Sudut Antara Kedua Vektor ===" << endl;
	angle(arr1, arr2);
	
	cout << endl << "=== Dot Product Kedua Vektor ===" << endl;
	cout << "Dot Product antara Dua Vektor Tersebut adalah : " << dot(arr1, arr2) << endl;
	
	cout << endl << "=== Proyeksi Vektor ===" << endl;
	cout << "Proyeksi Vektor A ke Vektor B adalah : "; proyeksi(arr1, arr2); cout << endl;
	cout << "Proyeksi Vektor B ke Vektor A adalah : "; proyeksi(arr2, arr1);
	cout << endl;
	
	cout << endl << "=== Cross Product Kedua Vektor ===" << endl;
	crossProduct(arr1,arr2);

	return 0;
}
