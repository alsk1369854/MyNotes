import math


def general_binary_search(arr_data, target_value):
    low_index = 0;
    hight_index = len(arr_data)-1;
    while low_index <= hight_index:
        middle_index = math.floor((hight_index + low_index)/2)
        if arr_data[middle_index] == target_value:
            return middle_index;
        elif arr_data[middle_index] < target_value:
            low_index = middle_index+1
        else:
            hight_index = middle_index-1
    return -1


def recursive_binary_search(arr_data, low_index, hight_index, target_value):
    middle_index = math.floor((hight_index + low_index) / 2)
    if arr_data[middle_index] == target_value:
        return middle_index
    elif low_index == hight_index:
        return -1
    elif arr_data[middle_index] < target_value:
        return recursive_binary_search(arr_data, middle_index+1, hight_index, target_value)
    else:
        return recursive_binary_search(arr_data, low_index, middle_index-1, target_value)


if __name__ == "__main__":
    print("Binary_Search")

    # create test data
    test_data = [10, -5, 98, 77, 65, 75, -45, 3, 4]

    # sort test_data
    test_data.sort()
    # print sorted test_data
    print(test_data)

    # fint_element
    result = general_binary_search(test_data, 3)
    # result = recursive_binary_search(test_data, 0, len(test_data)-1, 3)
    print(result)