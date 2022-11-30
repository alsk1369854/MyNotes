import tensorflow as tf

# MSE
# Cross Entropy Loss
# Hinge Loss

# # MSE ---------------
# y = tf.constant([1, 2, 3, 0, 2])
# y = tf.one_hot(y, depth=4)
# y = tf.cast(y, dtype=tf.float32)

# out = tf.random.normal([5, 4])

# loss1 = tf.reduce_mean(tf.square(y-out))
# print(loss1) # tf.Tensor(0.44715032, shape=(), dtype=float32)
# loss2 = tf.square(tf.norm(y-out))/(5*4)
# print(loss2) # tf.Tensor(0.4471503, shape=(), dtype=float32) 
# loss3 = tf.reduce_mean(tf.losses.MSE(y, out))
# print(loss3) # tf.Tensor(0.44715032, shape=(), dtype=float32)


# Cross Entropy Loss -------------------------
# Entropy 熵 =========================================================
# 驚喜度低，熵高
out1 = tf.constant([0.25, 0.25, 0.25, 0.25])
entropy1 = -tf.reduce_sum(out1*(tf.math.log(out1)/tf.math.log(2.)))
print(entropy1) # tf.Tensor(2.0, shape=(), dtype=float32)
# 驚喜度中，熵中等
out2 = tf.constant([0.1, 0.1, 0.7, 0.1])
entropy2 = -tf.reduce_sum(out2*(tf.math.log(out2)/tf.math.log(2.)))
print(entropy2) # tf.Tensor(1.3567796, shape=(), dtype=float32)
# 驚喜度高，熵低
out3 = tf.constant([0.01, 0.97, 0.01, 0.01])
entropy3 = -tf.reduce_sum(out3*(tf.math.log(out3)/tf.math.log(2.)))
print(entropy3) # tf.Tensor(0.24194068, shape=(), dtype=float32)

