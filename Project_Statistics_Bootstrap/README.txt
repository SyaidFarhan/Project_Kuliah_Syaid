# Bootstrap Sampling and Confidence Intervals

This Python script demonstrates the process of bootstrap sampling to estimate the confidence interval of the mean of a sample. It uses random resampling to generate multiple sample means and calculates the confidence interval using quantiles. Additionally, it calculates the confidence interval using the z-score method for comparison.


## Description

This script reads a dataset from a CSV file (`dataset.csv`), performs bootstrap sampling to estimate the confidence interval of the mean, and calculates the confidence interval using the z-score method. It visualizes the distribution of sample means, marks the confidence interval bounds, and compares the two methods.

## Features

- Reads dataset from a CSV file.
- Performs bootstrap sampling to estimate the confidence interval.
- Calculates the confidence interval using the z-score method.
- Visualizes the distribution of sample means and confidence intervals.

## Usage

1. Clone the repository or download the `script.py` file along with the `dataset.csv` file.
2. Ensure you have the required libraries installed (`pandas`, `numpy`, `matplotlib`).
3. Run the script using a Python interpreter:

   ```sh
   python script.py

