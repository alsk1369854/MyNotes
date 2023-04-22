import pymysql  # import pymysql library


class DBUtil:
    @staticmethod
    def get_connect():
        # get database connection
        cnn = pymysql.connect(host='127.0.0.1', port=3306,
                              user='root', passwd='root', db='testDB')
        # get query cursor
        cursor = cnn.cursor()
        return cnn, cursor

    @staticmethod
    def close_resource(*args):
        for resource in args:
            resource.close()

    @staticmethod
    def columns_name_of_index_map(cursor):
        results = {}
        column = 0
        for d in cursor.description:
            results[d[0]] = column
            column = column + 1

        return results
