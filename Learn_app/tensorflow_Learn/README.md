# Tensorflow
- 1. Tensor 類型
- 2. Tensor 創建
- 3. Tensor 索引與切片
- 4. Tensor 維度變換
- 5. Tensor 廣展
- 6. Tensor 數學運算

## 1. Tensor 類型
- 1-1. Tensor 支持的類型
- 1-2. Tensor 使用 cpu/gpu 的資源
- 1-3. Tensor to Numpy
- 1-4. Tensor 維度訊息

### 1-1. Tensor 支持的類型
- int, float, double
- bool
- string

Tensor 在創建類型時會自動識別數據類型，若要指定類型的話就需給定 'dtype' 參數
- int:   dtype=tf.int8 | int16 | int32 | int64 
- float: dtype=tf.float16 | float32 | float64 | double
- bool:  dtype=tf.bool
- string:dtype=tf.string
```python
# int 自動辨識類型
t_int = tf.convert_to_tensor(1)
print(t_int)
# tf.Tensor(1, shape=(), dtype=int32)

# float 自動辨識類型
t_float = tf.convert_to_tensor(1.)
print(t_float)
# tf.Tensor(1.0, shape=(), dtype=float32)

# 指定類型為 double 
t_double = tf.convert_to_tensor(1, dtype=tf.double) 
print(t_double)
# tf.Tensor(1.0, shape=(), dtype=float64)
```

### 1-2. Tensor 使用 cpu/gpu 的資源
cpu 與 gpu 資源不能互通，只能訪問到同資源底下的資料
```python
# Tensor 使用 cpu 或 gpu 資源
with tf.device('cpu'):
    t_cpu = tf.convert_to_tensor(1.)
with tf.device('gpu'):
    t_gpu = tf.convert_to_tensor(2.)

print(t_cpu.device)
# /job:localhost/replica:0/task:0/device:CPU:0
print(t_gpu.device)
# /job:localhost/replica:0/task:0/device:GPU:0
```

### 1-3. Tensor to Numpy
```python
# Tensor to Numpy
data = tf.range(5)
print(data)
# tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)

result = data.numpy()
print(result)
# [0 1 2 3 4]
```

### 1-4. Tensor 維度訊息
- .ndim : 獲取維度
- .shape : 獲取維度結構
- .dtype : 獲取 tensor 數據類型
- tf.rank() : 獲取 tensor 類型的，目標 tensor 維度

```python
data = tf.ones([2, 3, 4])
print(data)
# tf.Tensor(
# [[[1. 1. 1. 1.]
#   [1. 1. 1. 1.]
#   [1. 1. 1. 1.]]
#  [[1. 1. 1. 1.]
#   [1. 1. 1. 1.]
#   [1. 1. 1. 1.]]], shape=(2, 3, 4), dtype=float32)

# 取得 tensor 維度
print(data.ndim)
# 3

# 取得 tensor 維度結構
print(data.shape)
# (2, 3, 4)

# 取得 tensor 數據類型
print(data.dtype)
# <dtype: 'float32'>

# 獲取 tensor 類型的，目標 tensor 維度
print(tf.rank(data))
tf.Tensor(3, shape=(), dtype=int32)
```

## 2. Tensor 創建 
- 2-1. 從 Numpy 或 List 型態轉為 Tensor 型態
- 2-2. 初始化 0. Tensor
- 2-3. 初始化 1. Tensor
- 2-4. 初始化 值填充創建 Tensor

### 2-1. 從 Numpy 或 List 型態轉為 Tensor 型態
```python
# 1. 從 Numpy 轉 Tensor ***數據類型為 float64***
tf.convert_to_tensor(np.zeros([2,2]))
# tf.Tensor(
# [[0. 0.]
#  [0. 0.]], shape=(2, 2), dtype=float64)

# 2. 從 List 轉 Tensor 
tf.convert_to_tensor([1, 2.])
# tf.Tensor([1. 2.], shape=(2,), dtype=float32)

# 3. 不可變的 Tensor
print(tf.constant([1,2,3]))
# tf.Tensor([1 2 3], shape=(3,), dtype=int32) 
```

### 2-2. 初始化 0 Tensor
```python
# 1. 初始化為 0. Tensor
tf.zeros([1,2])
# tf.Tensor([[0. 0.]], shape=(1, 2), dtype=float32)

# 2. 根據 shape 初始化 0. Tensor, 下方兩種解果相同
a = tf.convert_to_tensor([2.,3.,5.])
tf.zeros_like(a)
# tf.Tensor([0. 0. 0.], shape=(3,), dtype=float32)
tf.zeros(a.shape)
# tf.Tensor([0. 0. 0.], shape=(3,), dtype=float32)
```

### 2-3. 初始化 1 Tensor
```python
# 1. 初始化為 1. Tensor
tf.ones([1,2])
# tf.Tensor([[1. 1.]], shape=(1, 2), dtype=float32)

# 2. 根據 shape 初始化 1. Tensor, 下方兩種解果相同
a = tf.convert_to_tensor([2.,3.,5.])
tf.ones_like(a)
# tf.Tensor([1. 1. 1.], shape=(3,), dtype=float32)
tf.ones(a.shape)
# tf.Tensor([1. 1. 1.], shape=(3,), dtype=float32)
```

### 2-4. 初始化 值填充創建 Tensor
```python
# 1. 定值填充創建
print(tf.fill([1,2], 5))
# tf.Tensor([[5 5]], shape=(1, 2), dtype=int32)

# 2. 平均填充創建, (平均=1, 標準差=1)
print(tf.random.normal([1,2], mean=1, stddev=1))
# tf.Tensor([[0.52998435 1.838253  ]], shape=(1, 2), dtype=float32)

# 3. 平均截斷填充, 截斷常態分佈兩端, 避免訓練時梯度消失, (平均=0, 標準差=1)
print(tf.random.truncated_normal([1,2], mean=0., stddev=1.))
# tf.Tensor([[1.5518807 0.0967934]], shape=(1, 2), dtype=float32)

# 4. 區間抽樣填充
print(tf.random.uniform([10], minval=0, maxval=10, dtype=tf.int32))
# tf.Tensor([6 0 0 5 2 1 3 0 9 5], shape=(10,), dtype=int32)

# 5. 範圍填充
print(tf.range(0, 10))
# tf.Tensor([0 1 2 3 4 5 6 7 8 9], shape=(10,), dtype=int32)

# 6. 資料打散
a = tf.convert_to_tensor([[1,2],[3,4],[5,6]])
print(tf.random.shuffle(a))
# tf.Tensor(
# [[3 4]
#  [1 2]
#  [5 6]], shape=(3, 2), dtype=int32)

# 7. gather() 依 index 映射取值
index = tf.range(9, -1, -1)
# tf.Tensor([9 8 7 6 5 4 3 2 1 0], shape=(10,), dtype=int32)
data = tf.random.uniform([10], maxval=10 ,dtype=tf.int32)
# tf.Tensor([8 9 5 8 4 3 9 1 9 0], shape=(10,), dtype=int32)
print(tf.gather(data, index))
# tf.Tensor([0 9 1 9 3 4 8 5 9 8], shape=(10,), dtype=int32)
```
<br/>

## 3. Tensor 索引與切片
- 3-1. 基本索引方式
- 3-2. Start:End
- 3-3. Start:End:Step
- 3-4. "..." 省略號
- 3-5. gather()
- 3-6. gather_nd()
- 3-7. boolean_mask()

### 3-1. 基本索引方式
```python
# [case, b, h, w, rgb]
a = tf.random.normal([5, 10, 28, 28, 3])
print(a[0][1].shape)
# (28, 28, 3)
print(a[0, 1].shape)
# (28, 28, 3)
```

### 3-2. Start:End
返回一段，包含 Start 不包含 End
```python
# 1. 單維度操作
a = tf.range(10)
# tf.Tensor([0 1 2 3 4 5 6 7 8 9], shape=(10,), dtype=int32)
print(a[:-1])
# tf.Tensor([0 1 2 3 4 5 6 7 8], shape=(9,), dtype=int32)
print(a[1:])
# tf.Tensor([1 2 3 4 5 6 7 8 9], shape=(9,), dtype=int32)
print(a[1:9])
# tf.Tensor([1 2 3 4 5 6 7 8], shape=(8,), dtype=int32)
print(a[-1,1,-1])


# 2. 多維度操作, 取所有圖片 R 通道 
# [case, b, h, w, rgb]
b = tf.random.normal([5, 10, 28, 28, 3])
print(b[:, :, :, :, 0].shape)
# (5, 10, 28, 28)
```

### 3-3. Start:End:Step
```python
# 1. 單維度操作
a = tf.range(10)
# tf.Tensor([0 1 2 3 4 5 6 7 8 9], shape=(10,), dtype=int32)
print(a[::-1])
# tf.Tensor([9 8 7 6 5 4 3 2 1 0], shape=(10,), dtype=int32)
print(a[1:-1:2])
# tf.Tensor([1 3 5 7], shape=(4,), dtype=int32)

# 2. 多維度操作, 取一半圖片資料並且只取 R 通道
# [case, b, h, w, rgb]
b = tf.random.normal([5, 10, 28, 28, 3])
print(b[:, ::2, :, :, 0].shape)
# (5, 5, 28, 28)
```

### 3-4. "..." 省略號
```python
# [case, b, h, w, rgb]
a = tf.random.normal([5, 10, 28, 28, 3])

# 取所有圖片 R 通道 
print(a[...,0].shape)
# (5, 10, 28, 28)

# 取第一個 case, 第一張圖的 R 通道
print(a[0,0,...,0].shape)
# (28, 28)
```

### 3-5. gather()
```python
# data = [classes, students, subjects]
data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# axis: 操作索引, indices: 取值索引
# 取班級 0, 3
print(tf.gather(data, axis=0, indices=[0, 3]).shape)
# (2, 35, 8)

# 取每個班級的 3, 7, 11 號學生
print(tf.gather(data, axis=1, indices=[3, 7, 11]).shape)
# (4, 3, 8)
```

### 3-6. gather_nd()
```python
# 3-6. gather_nd() =====================================================
# data = [classes, students, subjects]
data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# indices: 取值索引
# 取以下條件
#   0 班級 1,2 號學生
#   1 班級 4,5 號學生
print(tf.gather_nd(data, indices=[[0,1], [0,2], [1,4], [1,5]]).shape)
# (4, 8)
```

### 3-7. boolean_mask()
```python
# data = [classes, students, subjects]
data = tf.random.uniform([4,3,2], maxval=100, dtype=tf.int32)

# axis: 操作索引, mask: 是否留下此索引
# 取班級 2,3
result = tf.boolean_mask(data, axis=0, mask=[False, False, True, True])
print(result.shape)
# (2, 3, 2)

# 取以下條件
#   每個班級 0 號學生，第 1 個科目
#   每個班級 1 號學生，第 0 個科目
#   2 號不取
result = tf.boolean_mask(data, axis=1, mask=[
        [False, True], 
        [True, False],
        [False, False]])
print(result.shape)
# (4, 2)
```
<br/>

## 4. Tensor 維度變換
- 4-1. reshape() 維度重塑
- 4-2. transpose() 維度調換
- 4-3. expand_dims() 維度提升
- 4-4. squeeze() 維度下降
  
### 4-1. reshape() 維度重塑
```python
# data = [b, h, w, rgb]
data = tf.random.normal([5, 28, 28, 3])

# 一般降維
result = tf.reshape(data, [5, 28*28, 3])
print(result.shape)
# (5, 784, 3)

# 使用 -1, 自動計算降維, "-1"只能出現一次
result = tf.reshape(data, [5, -1, 3])
print(result.shape)
# (5, 784, 3)

# 升維
#   [5, 2352] -> [5, 28, 28, 3]
b = tf.reshape(data, [5, -1]) # (5, 2352)
result = tf.reshape(b, [5, 28, 28, 3])
print(result.shape)
# (5, 28, 28, 3)
```

### 4-2. transpose() 維度調換
```python
# data = [b, h, w, rgb]
data = tf.random.normal([5, 28, 28, 3])

# perm: 維度調換index|預設為反轉
# 維度反轉
#   [b, h, w, rgn] -> [rgb, w, h, b]
result = tf.transpose(data)
print(result.shape)
# (3, 28, 28, 5)

# 維度反轉: 高寬交換
#   [b, h, w, rgn] -> [b, w, h, rgn]
result = tf.transpose(data, perm=[0, 2, 1, 3])
print(result.shape)
# (5, 28, 28, 3)

# 維度反轉: 通道在前
#   [b, h, w, rgn] -> [b, rgb, h, w]
result = tf.transpose(data, perm=[0, 3, 1, 2])
print(result.shape)
# (5, 3, 28, 28)
```

### 4-3. expand_dims() 維度提升
```python
# data = [classes, students, subjects]
data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# axis: 操作插入維度(會在 "axis" 與 "axis-1" 之間插入 1 個維度)
# axis正號: 向前插入
result = tf.expand_dims(data, axis=0)
print(result.shape)
# (1, 4, 35, 8)

# axis負號: 向後插入
result = tf.expand_dims(data, axis=-3)
print(result.shape)
# (4, 1, 35, 8)
```

### 4-4. squeeze() 維度下降
```python
data = tf.zeros([1, 2, 1, 1, 3])

# axis: 操作維度|預設全部維度, (可將元素為 1 的維度消除)
# 不指定 axis 消除 Tensor 中所有元素為 1 的維度
result = tf.squeeze(data)
print(result.shape)
# (2, 3)

# 消除指定元素為 1 的維度
result = tf.squeeze(data, axis=-2)
print(result.shape)
# (1, 2, 1, 3)
```
<br/>


## 5. Tensor 廣展
將一向量廣展為指定的多維向量
- 5-1. broadcast_to() :使用參照的方式複製Tensor，不佔用多餘的記憶體空間(用於在訓練時添加基本常量)
- 5-2. tile() :與broadcast_to()雷同，但此方法實質複製了Tensor並佔用記憶體空間

### 5-1. broadcast_to() 使用範例
```python
# data = [classes, students, subjects]
data = tf.ones([2,2,3])
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]
#  [[1. 1. 1.]
#   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)

# 使用 + 運算符可自動的完成 Tensor 的 broadcast
# 可在目標 Tensor 維度中插入 1 元素來自動計算廣展維度，成為目的 Tensor 維度

# (一)使用 + 運算符自動完成broadcast與矩陣相加
# 將每個課目分數各加 5 分
_add = tf.fill([3], 5.)
print(_add)
# tf.Tensor([5. 5. 5.], shape=(3,), dtype=float32)
result = data + _add
print(result)
# tf.Tensor(
# [[[6. 6. 6.]
#   [6. 6. 6.]]
#  [[6. 6. 6.]
#   [6. 6. 6.]]], shape=(2, 2, 3), dtype=float32)

# (二)使用 + 運算符自動完成broadcast與矩陣相加
# 將每個課目分數各加 5 分
_add = tf.fill([1,2,1], 5.)
print(_add)
# tf.Tensor(
# [[[5.]
#   [5.]]], shape=(1, 2, 1), dtype=float32)
result = data + _add
print(result)
# tf.Tensor(
# [[[6. 6. 6.]
#   [6. 6. 6.]]
#  [[6. 6. 6.]
#   [6. 6. 6.]]], shape=(2, 2, 3), dtype=float32)

# 使用 broadcast_to() 將 [3] -> [2,2,3]
data = tf.ones([3])
print(data)
# tf.Tensor([1. 1. 1.], shape=(3,), dtype=float32)
result = tf.broadcast_to(data, [2,2,3])
print(result)
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]
#  [[1. 1. 1.]
#   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)
```
### 5-2. tile() 使用範例
```python
# data = [classes, students, subjects]
data = tf.ones([1,2,3])
print(data)
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]], shape=(1, 2, 3), dtype=float32)

# 可在 tile() 方法中傳入 "multiples" 維度，內可插入 1 元素來自動計算廣展維度

# 複製成兩個班
result = tf.tile(data, multiples=[2,1,1])
print(result)
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]
#  [[1. 1. 1.]
#   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)
```

## 6. Tensor 數學運算
- 6-1. (+, -, *, /)
- 6-2. (tf.math.log(), tf.exp())
- 6-3. (pow(), sqrt())
- 6-4. (@, matmul()) 矩陣相乘

### 6-1. (+, -, *, /)
```python
a = tf.ones([2,2])
# tf.Tensor(
# [[1. 1.]
#  [1. 1.]], shape=(2, 2), dtype=float32)
b = tf.fill([2,2], 2.)
# tf.Tensor(
# [[2. 2.]
#  [2. 2.]], shape=(2, 2), dtype=float32) 

# +
result = a + b
print(result)
# tf.Tensor(
# [[3. 3.]
#  [3. 3.]], shape=(2, 2), dtype=float32)

# - 
result = a - b
print(result)
# tf.Tensor(
# [[-1. -1.]
#  [-1. -1.]], shape=(2, 2), dtype=float32)

# * 
result = a * b
print(result)
# tf.Tensor(
# [[2. 2.]
#  [2. 2.]], shape=(2, 2), dtype=float32)

# /
result = a / b
print(result)
# tf.Tensor(
# [[0.5 0.5]
#  [0.5 0.5]], shape=(2, 2), dtype=float32)
```

### 6-2. (tf.math.log(), tf.exp())
- tf.math.log(x) :底預設 log<sub> e</sub><sup>x</sup> 如需換底則許須使用換底公式 (log<sub>e </sub>x / log<sub>e </sub>y = log<sub>y </sub>x)
- tf.exp(x) : e<sup>x</sup>

#### tf.math.log()
```python
data = tf.ones([2,2])
# tf.Tensor(
# [[1. 1.]
#  [1. 1.]], shape=(2, 2), dtype=float32)

# log e^1 = 0
result = tf.math.log(data)
print(result)
# tf.Tensor(
# [[0. 0.]
#  [0. 0.]], shape=(2, 2), dtype=float32)

# log 2^8 = log e^8 / log e^2
result = tf.math.log([8.]) / tf.math.log([2.])
print(result)
# tf.Tensor([3.], shape=(1,), dtype=float32)
```

#### tf.exp()
```python
data = tf.ones([2,2])
# tf.Tensor(
# [[1. 1.]
#  [1. 1.]], shape=(2, 2), dtype=float32)

result = tf.exp(data)
print(result)
# tf.Tensor(
# [[2.7182817 2.7182817]
#  [2.7182817 2.7182817]], shape=(2, 2), dtype=float32)
```

### 6-3. (pow(), sqrt())
- pow(x, y)
$$pow(x, y) = x^y$$
- sqrt(x)
$$sqrt(x) = \sqrt{x}$$

#### pow()
```python
data = tf.fill([2,2], 2.)
# tf.Tensor(
# [[2. 2.]
#  [2. 2.]], shape=(2, 2), dtype=float32)

result = data**2
print(result)
# tf.Tensor(
# [[4. 4.]
#  [4. 4.]], shape=(2, 2), dtype=float32)

result = tf.pow(data, 2)
print(result)
# tf.Tensor(
# [[4. 4.]
#  [4. 4.]], shape=(2, 2), dtype=float32)
```

#### sqrt()
```python
data = tf.fill([2,2], 4.)
# tf.Tensor(
# [[4. 4.]
#  [4. 4.]], shape=(2, 2), dtype=float32)

result = tf.sqrt(data)
print(result)
# tf.Tensor(
# [[2. 2.]
#  [2. 2.]], shape=(2, 2), dtype=float32)
```

### 6-4. (@, matmul()) 矩陣相乘
在 Tenser 矩陣乘法中是以最低的2維度來做運算, 3維以上不變 
```python
a = tf.fill([2,2], 2.)
# tf.Tensor(
# [[2. 2.]
#  [2. 2.]], shape=(2, 2), dtype=float32)
b = tf.ones([2,2])
# tf.Tensor(
# [[1. 1.]
#  [1. 1.]], shape=(2, 2), dtype=float32)

# @
result = a @ b
print(result)
# tf.Tensor(
# [[4. 4.]
#  [4. 4.]], shape=(2, 2), dtype=float32)

# matnul()
result = tf.matmul(a, b)
print(result)
# tf.Tensor(
# [[4. 4.]
#  [4. 4.]], shape=(2, 2), dtype=float32)

# 在 Tenser 矩陣乘法中是以最低的2維度來做運算, 3維以上不變 
# 下方運算為 [3*2] @ [2*4] = [3*4]
a = tf.random.normal([5, 7, 3, 2])
b = tf.random.normal([5, 7, 2, 4])
result = a @ b
print(result.shape)
# (5, 7, 3, 4)
```

