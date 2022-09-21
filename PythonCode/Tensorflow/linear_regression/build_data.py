import random
import numpy as np

with open('data.csv', 'w') as f:
    for step in range(120):
        x = str(random.random() * 10)
        y = str(random.random() * 10)
        f.write(x + ',' + y + '\n')

points = np.genfromtxt('data.csv', delimiter=',')
print(points.shape)