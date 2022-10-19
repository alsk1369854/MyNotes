from flask import Flask, render_template, request
from data import person_list
import uuid

# 設定當前執行模組
app = Flask(__name__)


# 設計 API 接口
@app.route('/')  # web router
def home():
    # response Web Page
    return render_template('index.html')


# 用戶登入
@app.route('/login', methods=['POST'])
def login():
    print(request.form)
    # 從 post 請求中拿數據
    username = request.form.get('username')
    password = request.form.get('password')
    print(person_list)
    return render_template('admin.html', username=username, password=password, person_list=person_list)


# 刪除 Person
@app.route('/delete/<id>')
def delete(id):
    print(id)
    # 刪除對應的 person
    for person in person_list:
        if person['id'] == id:
            person_list.remove(person)
    return render_template('admin.html', person_list=person_list)


# 更改 Person 訊息
@app.route('/change/<id>')
def change(id):
    print(id)
    for person in person_list:
        if person['id'] == id:
            return render_template('change.html', person=person)

    return render_template('admin.html', person_list=person_list)


@app.route('/doChange', methods=['POST'])
def doChange():
    id = request.form.get('id')
    name = request.form.get('name')
    age = request.form.get('age')
    for person in person_list:
        if person['id'] == id:
            person['name'] = name
            person['age'] = age
    return render_template('admin.html', person_list=person_list)


@app.route('/add')
def add():
    return render_template('add.html')


@app.route('/doAdd', methods=['POST'])
def doAdd():
    person = {
        'id': uuid.uuid1(),
        'name': request.form.get('name'),
        'age': request.form.get('age')
    }

    person_list.insert(0, person)
    return render_template('admin.html', person_list=person_list)


@app.route('/test')
def test():
    return 'test page'


if __name__ == '__main__':
    # host 設為 0.0.0.0 外網才能透過 ip 訪問
    app.run(host="0.0.0.0", port=8888)
