import tensorflow as tf
import numpy as np

# # 1-1. Tensor 支持的類型 ============================================================================
# # int, float, double
# # bool
# # string
# # int:   dtype=tf.int8|int16|int32|int64 
# # float: dtype=tf.float16|float32|float64|double
# # bool:  dtype=tf.bool
# # string:dtype=tf.string

# # int 自動辨識類型
# t_int = tf.convert_to_tensor(1)
# print(t_int)
# # tf.Tensor(1, shape=(), dtype=int32)

# # float 自動辨識類型
# t_float = tf.convert_to_tensor(1.)
# print(t_float)
# # tf.Tensor(1.0, shape=(), dtype=float32)

# # 指定類型為 double 
# t_double = tf.convert_to_tensor(1, dtype=tf.double) 
# print(t_double)
# # tf.Tensor(1.0, shape=(), dtype=float64)


# # 1-2. Tensor 使用 cpu/gpu 的資源 ======================================================================================
# # cpu 與 gpu 資源不能互通，只能訪問到同資源底下的資料

# # Tensor 使用 cpu 或 gpu 資源
# with tf.device('cpu'):
#     t_cpu = tf.convert_to_tensor(1.)
# with tf.device('gpu'):
#     t_gpu = tf.convert_to_tensor(2.)

# print(t_cpu.device)
# # /job:localhost/replica:0/task:0/device:CPU:0
# print(t_gpu.device)
# # /job:localhost/replica:0/task:0/device:GPU:0


# # 1-3. Tensor to Numpy =============================================
# # Tensor to Numpy
# data = tf.range(5)
# print(data)
# # tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)

# result = data.numpy()
# print(result)
# # [0 1 2 3 4]

# data = tf.ones([]) 
# # tf.Tensor(1.0, shape=(), dtype=float32)
# print(data.numpy()) # 1.0
# print(int(data)) # 1
# print(float(data)) # 1.0

# # 1-4. Tensor 維度訊息 =============================================
# data = tf.ones([2, 3, 4])
# print(data)
# # tf.Tensor(
# # [[[1. 1. 1. 1.]
# #   [1. 1. 1. 1.]
# #   [1. 1. 1. 1.]]
# #  [[1. 1. 1. 1.]
# #   [1. 1. 1. 1.]
# #   [1. 1. 1. 1.]]], shape=(2, 3, 4), dtype=float32)

# # 取得 tensor 維度
# print(data.ndim)
# # 3

# # 取得 tensor 維度結構
# print(data.shape)
# # (2, 3, 4)

# # 取得 tensor 數據類型
# print(data.dtype)
# # <dtype: 'float32'>

# # 獲取 tensor 類型的，目標 tensor 維度
# print(tf.rank(data))
# tf.Tensor(3, shape=(), dtype=int32)

# # 1-5. Tensor 數據類型轉換 =============================================================================
# data = tf.range(5)
# # tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)

# # int32 to float32
# result = tf.cast(data, dtype=tf.float32)
# print(result)
# # tf.Tensor([0. 1. 2. 3. 4.], shape=(5,), dtype=float32)

# # bool to int32
# data = tf.constant([True, False])
# # tf.Tensor([True False], shape=(2,), dtype=bool)
# result = tf.cast(data, dtype=tf.int32)
# print(result)
# # tf.Tensor([1 0], shape=(2,), dtype=int32)

# # int32 to bool
# data = tf.constant([0, 1])
# # tf.Tensor([0 1], shape=(2,), dtype=int32)
# result = tf.cast(data, dtype=tf.bool)
# print(result)
# # tf.Tensor([False  True], shape=(2,), dtype=bool)


# # 1-6. Variable 可訓練的 Tensor =======================================================================================
# data = tf.range(5)
# # tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)

# result = tf.Variable(data)
# print(result)
# # <tf.Variable 'Variable:0' shape=(5,) dtype=int32, numpy=array([0, 1, 2, 3, 4])>
# print(result.trainable)
# # True


# 1-7. 判斷是否為 Tensor or Variable 物件 =============================================================================================
data_tensor = tf.range(5)
# tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)
data_variable = tf.Variable(data_tensor)
# <tf.Variable 'Variable:0' shape=(5,) dtype=int32, numpy=array([0, 1, 2, 3, 4])>

# 推薦: Use is_tensor()
print(tf.is_tensor(data_tensor)) # True
print(tf.is_tensor(data_variable)) # True

# Use isinstance check data_variable
print(isinstance(data_variable, tf.Tensor)) # False
print(isinstance(data_variable, tf.Variable)) # True