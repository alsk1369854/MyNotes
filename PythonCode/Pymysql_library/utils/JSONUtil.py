import json


class JSONUtil:
    @staticmethod
    def obj_to_json(obj) -> str:
        return json.dumps(obj, default=JSONUtil.__obj_dict)

    @staticmethod
    def __obj_dict(obj):
        return obj.__dict__
