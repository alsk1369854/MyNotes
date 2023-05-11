# # tensorflow.keras.datasets: tensorflow 中自帶的測試資料集 
# # ==========================================================
# import tensorflow as tf
# from tensorflow.keras import datasets
# # 加載 mnist dataset 
# # ========================
# # all_data = [70k, 28, 28] 
# (x, y), (test_x, test_y) = datasets.mnist.load_data()
# print(x.shape, y.shape)
# # (60000, 28, 28) (60000,)
# print(test_x.shape, test_y.shape)
# # (10000, 28, 28) (10000,)
# print(x.min(), x.max(), x.mean())
# # (0 ,255, 33.318421449829934)
# print(y[:5])
# # [5 0 4 1 9]


# # 加載 cifar10 dataset 
# # ========================
# # all_data = [60k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()
# print(x.shape, y.shape)
# # (50000, 32, 32, 3) (50000, 1)
# print(test_x.shape, test_y.shape)
# # (10000, 32, 32, 3) (10000, 1)
# print(x.min(), x.max())
# # (0, 255)
# print(y[:5])
# # [[6]
# #  [9]
# #  [9]
# #  [4]
# #  [1]]

# # tf.data.Dataset.from_tensor_slices() 數據封裝為 tensor 數據集
# # ==================================================================
# import tensorflow as tf
# from tensorflow.keras import datasets

# # 加載 cifar10 dataset 
# # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()

# # x, y 封裝為 tensor dataset
# # ==========================
# db = tf.data.Dataset.from_tensor_slices((x, y))
# data = next(iter(db))
# print(len(data)) # 2
# print(data[0].shape) # (32, 32, 3)
# print(data[1].shape) # (1,)

# # x 封裝為 tensor dataset
# # ==========================
# db = tf.data.Dataset.from_tensor_slices(x)
# data = next(iter(db))
# print(data.shape) # (32, 32, 3)


# # # 15-2-1. shuffle(num): 數據隨機打亂程度
# # ==================================================================
# import tensorflow as tf
# from tensorflow.keras import datasets

# # 加載 cifar10 dataset 
# # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()
# db = tf.data.Dataset.from_tensor_slices((x, y))

# # 數據隨機打亂
# db = db.shuffle(buffer_siz=10000)


# # # 15-2-1. 15-2-2. map(map_func): 數據預處理
# # ==================================================================
# import tensorflow as tf
# from tensorflow.keras import datasets

# # 加載 cifar10 dataset 
# # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()
# db = tf.data.Dataset.from_tensor_slices((x, y))

# # 設置預處理函數
# def map_func(x, y):
#     # 預處理 x
#     x = tf.cast(x, dtype=tf.float32)/255
#     # 預處理 y
#     y = tf.cast(y, dtype=tf.int32)
#     y = tf.squeeze(y)
#     y = tf.one_hot(y, depth=10)
#     return x, y

# # 使用 map() 預處理所有資料集
# db2 = db.map(map_func=map_func)
# data = next(iter(db2))
# print(data[0].shape) # (32, 32, 3)
# print(data[1])
# # tf.Tensor([0. 0. 0. 0. 0. 0. 1. 0. 0. 0.], shape=(10,), dtype=float32)


# # # 15-2-3. batch(batch_size): 數據批量處理數量
# # ==================================================================
# import tensorflow as tf
# from tensorflow.keras import datasets

# # 加載 cifar10 dataset 
# # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()
# db = tf.data.Dataset.from_tensor_slices((x, y))

# # 設置一次處理 64 比資料
# db = db.batch(batch_size=64)
# data = next(iter(db))
# print(data[0].shape) # (64, 32, 32, 3)
# print(data[1].shape) # (64, 1)



# # # 15-2-4. repeat(num): 數據重新跌代次數
# # ==================================================================
# import tensorflow as tf
# from tensorflow.keras import datasets

# # 加載 cifar10 dataset 
# # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
# (x, y), (test_x, test_y) = datasets.cifar10.load_data()
# db = tf.data.Dataset.from_tensor_slices((test_x, test_y))

# count = 0
# iter_db = iter(db)
# for data in iter_db:
#     count += 1
# print('before len: ', count)
# # before len:  10000

# # 設定跌代循環2次
# db = db.repeat(count=2)
# count = 0
# iter_db = iter(db)
# for data in iter_db:
#     count += 1
# print('after len: ', count)
# # after len:  20000



# 15-3. 數據加載 完整使用範例
# ==================================================================
import tensorflow as tf
from tensorflow.keras import datasets

# 設計數據預處理函數
def cifar10_map_func(x, y):
    # x
    x = tf.cast(x, dtype=tf.float32)/255 # (0~255) -> (0~1)
    # y
    y = tf.squeeze(y) # [1] -> []
    y = tf.cast(y, dtype=tf.int32)
    y = tf.one_hot(y, depth=10) # [] -> [10]
    return x, y

# 設計數據加載函數
def get_cifar10_dataset():
    # 加載 cifar10 dataset 
    # all_data = [50k, 32, 32, 3], [10k, 32, 32, 3] 
    (x, y), (test_x, test_y) = datasets.cifar10.load_data()
    # 封裝訓練資料
    ds = tf.data.Dataset.from_tensor_slices((x, y))
    ds = ds.map(map_func=cifar10_map_func)
    ds = ds.shuffle(50000).batch(100)
    # 封裝測試資料
    ds_test = tf.data.Dataset.from_tensor_slices((test_x, test_y))
    ds_test = ds_test.map(map_func=cifar10_map_func)
    ds_test = ds_test.shuffle(10000).batch(100)
    
    return ds, ds_test

# 加載數據
ds, ds_test = get_cifar10_dataset()
data = next(iter(ds))
print(data[0].shape) # (100, 32, 32, 3)
print(data[1].shape) # (100, 10)