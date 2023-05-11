import tensorflow as tf

# output:[b, n], target:[b]
def accuracy(output, target, topk=(1,)):
    maxk = max(topk)
    batch_size = target.shape[0]

    # 取出最大前 topK 的預測結果
    pred = tf.math.top_k(output, maxk).indices # [b, maxk]
    # 矩陣轉置
    pred = tf.transpose(pred, perm=[1, 0]) # [maxk, b]
    # 真實解果 broadcast 成為與預測結果一樣大小的舉證
    target_ = tf.broadcast_to(target, pred.shape) # [maxk, b]
    correct = tf.equal(pred, target_) # [maxk, b]

    res = []
    for k in topk:
        # 取前 k 個預測結果，並將結果攤平轉換為 float32 數據類型
        correct_k = tf.cast(tf.reshape(correct[:k], [-1]), dtype=tf.float32) # [k, b]
        # 計算前 k 個預測結果，正確預測的數量
        correct_k = tf.reduce_sum(correct_k) # []
        # 除去所有結果總數量，取得前 k 向預測結果的正確率
        acc = float(correct_k / batch_size)
        res.append(acc)

    return res


# 模擬模型預測結果
output = tf.random.normal([10, 6])
# 使預測結果集總和為 1
output = tf.math.softmax(output, axis=1)
# 創建真實解果資料
target = tf.random.uniform([10], maxval=6, dtype=tf.int32)

# 計算 top 1~6 預測結果，對應的準確性
acc =  accuracy(output, target, topk=(1,2,3,4,5,6))

print('預測結果: ', output.numpy())
pred = tf.argmax(output, axis=1)
print('top1 預測結果: ', pred.numpy())
print('真實結果: ', target.numpy())
print('top 1~6 acc: ', acc)

# 預測結果:  [[0.01599683 0.2586538  0.03114775 0.41821274 0.13388869 0.14210023]
#  [0.03047033 0.4571253  0.42915204 0.04027861 0.01444509 0.0285286 ]
#  [0.02405828 0.13872029 0.36055857 0.09747908 0.27350754 0.1056762 ]
#  [0.11693911 0.18226306 0.30125073 0.08403484 0.2409016  0.07461069]
#  [0.3156007  0.07190048 0.08539322 0.08392893 0.20196776 0.24120893]
#  [0.02276766 0.11890458 0.42106277 0.19977042 0.20472538 0.03276917]
#  [0.12434467 0.04807299 0.2137635  0.1346938  0.12734754 0.3517775 ]
#  [0.04323702 0.18649292 0.09656099 0.21869545 0.02307978 0.43193394]
#  [0.0783204  0.10127778 0.4983185  0.22030185 0.05811384 0.04366755]
#  [0.04397693 0.17431746 0.15371111 0.05870872 0.4574907  0.11179502]]
# top1 預測結果:  [3 1 2 2 0 2 5 5 2 4]
# 真實結果:  [5 0 2 4 2 1 3 3 3 0]
# top 1~6 acc:  [0.10000000149011612, 0.4000000059604645, 0.6000000238418579, 0.8999999761581421, 0.8999999761581421, 1.0]

