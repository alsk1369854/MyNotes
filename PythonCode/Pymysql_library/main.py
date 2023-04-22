from datetime import datetime
from configs.DataFormatConfig import DataFormatConfig
from utils.DBUtil import DBUtil
from utils.JSONUtil import JSONUtil

from models.AttendanceRecord import AttendanceRecord
from models.Person import Person
from models.Room import Room


# inser
def insert_new_attendance_record(cursor, attendance_record: AttendanceRecord):
    # 使用站位符防 sql 注入攻擊
    # 定義插入 sql 語句
    sql = "insert into testDB.attendance_record (room, person, date_time_stamp) " \
          "values (%s, %s, %s)"

    # 執行 sql 語句
    cursor.execute(sql, (attendance_record.room._id,
                   attendance_record.person._id, attendance_record.date_time_stamp))


# read list
def get_room_records_between_two_datetime(cursor, room_id, start_datetime, end_datetime, order_by="_id"):
    # 使用站位符防 sql 注入攻擊
    # 查詢時間區段，房間中所監測到人物記錄(每個人物在區段時間內第一次被檢測到的記錄)
    sql = "select * " \
          "from attendance_record " \
          "where date_time_stamp in (	select min(date_time_stamp) as date_time_stamp " \
          "                             from testDB.attendance_record " \
          "                             where room = %s " \
          "                             and date_time_stamp between %s and %s " \
          "                             group by person) " \
          "order by %s ASC"

    # execute sql with insert values
    cursor.execute(sql, (room_id, start_datetime, end_datetime, order_by))

    # get list result with fetchall()
    result_list = cursor.fetchall()

    # create column title mapping result index obj
    columns_name_map = DBUtil.columns_name_of_index_map(cursor)

    # build model list
    attendance_record_list = []
    for result in result_list:
        _id = result[columns_name_map['_id']]  # attendance record id
        room = result[columns_name_map['room']]  # room id
        room = get_room_by_id(cursor, room)  # build Room model
        person = result[columns_name_map['person']]  # person id
        person = get_person_by_id(cursor, person)  # build Person model
        # attendance date_time_stamp :typeof date
        date_time_stamp = result[columns_name_map['date_time_stamp']]
        # attendance date_time_stamp :typeof str
        date_time_stamp = date_time_stamp.strftime(
            DataFormatConfig.DATETIME_FORMAT)
        attendance_record = AttendanceRecord(
            _id, room, person, date_time_stamp)  # build AttendanceRecord model
        attendance_record_list.append(attendance_record)

    return attendance_record_list


# read one
def get_attendance_record_by_id(cursor, item_id):
    # 使用站位符防 sql 注入攻擊
    # defined sql
    sql = "select * from attendance_record where _id = %s"

    # execute sql with insert values
    cursor.execute(sql, (item_id))

    # get single result with fetchone()
    result = cursor.fetchone()

    # create column title mapping result index obj
    columns_name_map = DBUtil.columns_name_of_index_map(cursor)

    # build model
    _id = result[columns_name_map['_id']]  # attendance id
    room = result[columns_name_map['room']]  # room id
    room = get_room_by_id(cursor, room)  # build Room model
    person = result[columns_name_map['person']]  # person id
    person = get_person_by_id(cursor, person)  # build Preson model
    # attendance date_time_stamp :typeof date
    date_time_stamp = result[columns_name_map['date_time_stamp']]
    # attendance date_time_stamp :typeof str
    date_time_stamp = date_time_stamp.strftime(
        DataFormatConfig.DATETIME_FORMAT)
    attendance_record = AttendanceRecord(
        _id, room, person, date_time_stamp)  # build AttendanceRecord model

    return attendance_record


# read one
def get_room_by_id(cursor, item_id):
    # 使用站位符防 sql 注入攻擊
    # defined sql
    sql = "select * from room where _id = %s"

    # execute sql with insert values
    cursor.execute(sql, (item_id))

    # get single result with fetchone()
    result = cursor.fetchone()

    # create column title mapping result index obj
    columns_name_map = DBUtil.columns_name_of_index_map(cursor)

    # build model
    _id = result[columns_name_map['_id']]
    name = result[columns_name_map['name']]
    webcam_device_id = result[columns_name_map['webcam_device_id']]
    room = Room(_id, name, webcam_device_id)

    return room


# read one
def get_person_by_id(cursor, item_id):
    # 使用站位符防 sql 注入攻擊
    # defined sql
    sql = "select * from person where _id = %s"

    # execute sql with insert values
    cursor.execute(sql, (item_id))

    # get single result with fetchone()
    result = cursor.fetchone()

    # create column title mapping result index obj
    columns_name_map = DBUtil.columns_name_of_index_map(cursor)

    # build model
    _id = result[columns_name_map['_id']]
    name = result[columns_name_map['name']]
    person = Person(_id, name)

    return person


if __name__ == '__main__':
    # start transaction
    conn, cursor = DBUtil.get_connect()

    # read data
    person = get_person_by_id(cursor, 1)
    # get_room_by_id(cursor, 1)
    # get_attendance_record_by_id(cursor, 1)
    # get_room_records_between_two_datetime(cursor,1, '2022-10-20 08:10:01', '2022-10-20 08:15:01')

    # insert data
    insert_new_attendance_record(cursor, AttendanceRecord(
        room=Room(_id=1), person=Person(_id=1), date_time_stamp=datetime.now().strftime(DataFormatConfig.DATETIME_FORMAT)))

    # end transaction
    conn.commit()
    DBUtil.close_resource(cursor, conn)

    # model to json
    person_json = JSONUtil.obj_to_json(person)
