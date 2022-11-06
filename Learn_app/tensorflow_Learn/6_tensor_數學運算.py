import tensorflow as tf

# # 6-1. (+, -, *, /) ===========================================
# a = tf.ones([2,2])
# # tf.Tensor(
# # [[1. 1.]
# #  [1. 1.]], shape=(2, 2), dtype=float32)
# b = tf.fill([2,2], 2.)
# # tf.Tensor(
# # [[2. 2.]
# #  [2. 2.]], shape=(2, 2), dtype=float32) 

# # +
# result = a + b
# print(result)
# # tf.Tensor(
# # [[3. 3.]
# #  [3. 3.]], shape=(2, 2), dtype=float32)

# # - 
# result = a - b
# print(result)
# # tf.Tensor(
# # [[-1. -1.]
# #  [-1. -1.]], shape=(2, 2), dtype=float32)

# # * 
# result = a * b
# print(result)
# # tf.Tensor(
# # [[2. 2.]
# #  [2. 2.]], shape=(2, 2), dtype=float32)

# # /
# result = a / b
# print(result)
# # tf.Tensor(
# # [[0.5 0.5]
# #  [0.5 0.5]], shape=(2, 2), dtype=float32)


# # 6-2. (tf.math.log(), tf.exp()) ================================
# ## tf.math.log() ==================
# data = tf.ones([2,2])
# # tf.Tensor(
# # [[1. 1.]
# #  [1. 1.]], shape=(2, 2), dtype=float32)

# # log e^1 = 0
# result = tf.math.log(data)
# print(result)
# # tf.Tensor(
# # [[0. 0.]
# #  [0. 0.]], shape=(2, 2), dtype=float32)

# # log 2^8 = log e^8 / log e^2
# result = tf.math.log([8.]) / tf.math.log([2.])
# print(result)
# # tf.Tensor([3.], shape=(1,), dtype=float32)

# # tf.exp() ==================
# data = tf.ones([2,2])
# # tf.Tensor(
# # [[1. 1.]
# #  [1. 1.]], shape=(2, 2), dtype=float32)

# result = tf.exp(data)
# print(result)
# # tf.Tensor(
# # [[2.7182817 2.7182817]
# #  [2.7182817 2.7182817]], shape=(2, 2), dtype=float32)

# # 6-3. (pow(), sqrt()) ========================================================================
# # pow() ============
# data = tf.fill([2,2], 2.)
# # tf.Tensor(
# # [[2. 2.]
# #  [2. 2.]], shape=(2, 2), dtype=float32)

# result = data**2
# print(result)
# # tf.Tensor(
# # [[4. 4.]
# #  [4. 4.]], shape=(2, 2), dtype=float32)

# result = tf.pow(data, 2)
# print(result)
# # tf.Tensor(
# # [[4. 4.]
# #  [4. 4.]], shape=(2, 2), dtype=float32)

# # sqrt() =============
# data = tf.fill([2,2], 4.)
# # tf.Tensor(
# # [[4. 4.]
# #  [4. 4.]], shape=(2, 2), dtype=float32)

# result = tf.sqrt(data)
# print(result)
# # tf.Tensor(
# # [[2. 2.]
# #  [2. 2.]], shape=(2, 2), dtype=float32)


# # 6-4. (@, matmul()) 矩陣相乘 ==============================================================================================
# a = tf.fill([2,2], 2.)
# # tf.Tensor(
# # [[2. 2.]
# #  [2. 2.]], shape=(2, 2), dtype=float32)
# b = tf.ones([2,2])
# # tf.Tensor(
# # [[1. 1.]
# #  [1. 1.]], shape=(2, 2), dtype=float32)

# # @
# result = a @ b
# print(result)
# # tf.Tensor(
# # [[4. 4.]
# #  [4. 4.]], shape=(2, 2), dtype=float32)

# # matnul()
# result = tf.matmul(a, b)
# print(result)
# # tf.Tensor(
# # [[4. 4.]
# #  [4. 4.]], shape=(2, 2), dtype=float32)


# # 在 Tenser 矩陣乘法中是以最低的2維度來做運算, 3維以上不變 
# # 下方運算為 [3*2] @ [2*4] = [3*4]
# a = tf.random.normal([5, 7, 3, 2])
# b = tf.random.normal([5, 7, 2, 4])
# result = a @ b
# print(result.shape)
# # (5, 7, 3, 4)
