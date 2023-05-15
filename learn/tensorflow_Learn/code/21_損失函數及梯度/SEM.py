import tensorflow as tf

x = tf.random.normal([2, 4])
w = tf.random.normal([4, 3])
b = tf.zeros([3])
y = tf.constant([2, 0])

lr = 0.01
with tf.GradientTape() as tape:
    tape.watch([w, b])
    one_hot = tf.one_hot(y, depth=3)
    prob = tf.nn.softmax(x@w+b, axis=1)
    loss_MSE = tf.losses.MSE(one_hot, prob)
    loss_mean = tf.reduce_mean(loss_MSE)

grads = tape.gradient(loss_mean, [w, b])
newW = w + lr * grads[0]
newB = b + lr * grads[1]