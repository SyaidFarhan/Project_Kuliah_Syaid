import random
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import openpyxl

data = pd.read_csv('dataset.csv')
m = 500000
sample = []
sample = data['Age'].sample(n=100, random_state=42).tolist()
Size_Sample = len(sample)
Stat_Sample = []

Stat_Sample=[]
for _ in range(m):
    Sample_Boots = random.choices(sample, k=Size_Sample)
    rata_Sample = np.mean(Sample_Boots)
    Stat_Sample.append(rata_Sample)
    
print('Sampling 1 : ')
print(sample)
Stat_Sample.sort()


confidence_level = 0.90
confidence_interval = pd.Series(Stat_Sample).quantile([0.5 - confidence_level/2, 0.5 + confidence_level/2])
lower_bound, upper_bound = confidence_interval.values

#Create a figure with subplots for one histograms
fig, ax1 = plt.subplots()

# Plot histogram of Stat_Sample
ax1.hist(Stat_Sample, bins=20, density=True, alpha=0.7)
ax1.axvline(x=lower_bound, color='red', linestyle='--', label='Lower Bound')
ax1.axvline(x=upper_bound, color='green', linestyle='--', label='Upper Bound')


# Calculate and plot the distribution curve based on Stat_Sample
x_stats = np.linspace(min(Stat_Sample), max(Stat_Sample), 100)
y_stats = np.exp(-0.5 * ((x_stats - np.mean(Stat_Sample)) / np.std(Stat_Sample)) ** 2) / (np.std(Stat_Sample) * np.sqrt(2 * np.pi))
ax1.plot(x_stats, y_stats, 'b-', linewidth=2)

ax1.set_xlabel('Mean')
ax1.set_ylabel('Probability')
ax1.set_title(f'Confidence Interval ({confidence_level * 100}%)')
ax1.legend()
ax1.grid(True)


z_scores = 1.645

std_sample = np.std(sample)

z_lower_bound =  np.mean(sample) - (z_scores * std_sample) / np.sqrt(Size_Sample)
z_upper_bound = np.mean(sample) + (z_scores * std_sample) / np.sqrt(Size_Sample)



plt.show()
print(" ")
print(f"Mean Sample : {np.mean(sample)}")
print(f"Standard Deviation Sample : {np.std(sample)}")
print(f"interval Konfidensi 90% dengan Bootstrap: from {lower_bound} to {upper_bound}")
print(f"interval Konfidensi 90% dengan z-score: from {z_lower_bound} to {z_upper_bound}")

