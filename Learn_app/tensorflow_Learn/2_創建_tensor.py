import tensorflow as tf
import numpy as np

# 從 Numpy 轉 Tensor ***數據類型為 float64*** ============================
# print(tf.convert_to_tensor(np.zeros([2,2])))

# 從 List 轉 Tensor
# print(tf.convert_to_tensor([1, 2.]))

# 不可變的 Tensor
# print(tf.constant([1,2,3]))
# tf.Tensor([1 2 3], shape=(3,), dtype=int32)

# 初始化為 0 Tensor =====================================================
# print(tf.zeros([1,2]))

# # 根據 shape 初始化 0 Tensor
# a = tf.convert_to_tensor([2.,3.,5.])
# print(tf.zeros_like(a))
# print(tf.zeros(a.shape))

# 初始化為 1 Tensor =====================================================
# print(tf.ones([1,2]))

# # 根據 shape 初始化 1 Tensor
# a = tf.convert_to_tensor([2.,3.,5.])
# print(tf.ones_like(a))
# print(tf.ones(a.shape))


# 2-4. 初始化 值填充創建 Tensor =================================================
# print(tf.fill([1,2], 5))

# 平均填充創建, 平均=1, 標準差=1
# print(tf.random.normal([1,2], mean=1, stddev=1))

# 平均截斷填充, 截斷常態分佈兩端, 避免訓練時梯度消失, (平均=0, 標準差=1)
# print(tf.random.truncated_normal([1,2], mean=0., stddev=1.))

# 4. 區間抽樣填充
# print(tf.random.uniform([10], minval=0, maxval=10, dtype=tf.int32))

# 5. 範圍填充
# print(tf.range(0, 10))

# 6. 資料打散
# a = tf.convert_to_tensor([[1,2],[3,4],[5,6]])
# print(tf.random.shuffle(a))

# 7. gather() 依 index 映射取值
# index = tf.range(9, -1, -1)
# # tf.Tensor([9 8 7 6 5 4 3 2 1 0], shape=(10,), dtype=int32)
# data = tf.random.uniform([10], maxval=10 ,dtype=tf.int32)
# # tf.Tensor([8 9 5 8 4 3 9 1 9 0], shape=(10,), dtype=int32)
# print(tf.gather(data, index))
# # tf.Tensor([0 9 1 9 3 4 8 5 9 8], shape=(10,), dtype=int32)










