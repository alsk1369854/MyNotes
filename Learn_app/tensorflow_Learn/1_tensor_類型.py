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


# 1-4. Tensor 維度訊息 =============================================
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