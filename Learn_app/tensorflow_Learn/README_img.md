# Tensorflow
## Installation
[Installation Video 1](https://www.youtube.com/watch?v=IsyEaa0mIVk&list=PLh7DRwYmUgh7swOvZUZ52LMeGDmjFH0nv&index=3)
[Installation Video 2](https://www.youtube.com/watch?v=X7ESTHXhKO4&list=PLh7DRwYmUgh7swOvZUZ52LMeGDmjFH0nv&index=5)
> [Anaconda](https://www.anaconda.com/products/distribution#Downloads) 
>
> [NVIDIA CUDA](https://developer.nvidia.com/cuda-downloads?target_os=Windows&target_arch=x86_64&target_version=10&target_type=exe_local)
>
> [cuDNN](https://developer.nvidia.com/rdp/cudnn-download)


```python
# cpu version
pip install --upgrade tensorflow
# gpu version
pip install --upgrade tensorflow-gpu
```

### Other
```python
# python online etdtor
pip install jupyter
# jupyter themes
pip install jupyterthemes
# get themes list
jt -l
# set themes 
jt -t theme-name -T -N
# start jupyter
Jupyter Notebook -port 8888
```
<br/>

![image info](./screenshots/network_api.png)


## linear regression（線性回歸）
![image info](./screenshots/12-1.png)
![image info](./screenshots/12-2.png)

## 數據類型
### Create
![image info](./screenshots/20-1.png)
### Tensorfloa property
![image info](./screenshots/20-2.png)
### Check tensor type
![image info](./screenshots/21-1.png)
### Convert
![image info](./screenshots/21-2.png)
### bool \ int
![image info](./screenshots/21-3.png)
### tf.Variable
![image info](./screenshots/21-4.png)
### To numpy
![image info](./screenshots/21-5.png)
<br/>

## Create Tensor(創建Tensor)
### From Numpy, List
![image info](./screenshots/22-1.png)
### tf.zeros
![image info](./screenshots/22-2.png)
### tf.zeros_like
![image info](./screenshots/22-3.png)
### tf.ones
![image info](./screenshots/22-4.png)
### Fill
![image info](./screenshots/22-5.png)
### Normal
![image info](./screenshots/22-6.png)
![image info](./screenshots/22-7.png)
### Uniform
![image info](./screenshots/23-1.png)
### Random Permutation
![image info](./screenshots/23-2.png)
### tf.constant
![image info](./screenshots/23-3.png)
### Loss
![image info](./screenshots/23-4.png)
### Vector
![image info](./screenshots/23-5.png)
### Matirx
![image info](./screenshots/24-1.png)
![image info](./screenshots/24-2.png)
![image info](./screenshots/24-3.png)