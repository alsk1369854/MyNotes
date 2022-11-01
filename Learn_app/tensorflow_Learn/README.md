# Tensorflow
-
- 2. 創建 Tensor
- 3. Tensor 索引與切片
- 4. Tensor 維度變換


## 2. 創建 Tensor
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
- 4-1. reshape() 維度升降
- 4-2. transpose() 維度調換
  
### 4-1. reshape() 維度升降
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
