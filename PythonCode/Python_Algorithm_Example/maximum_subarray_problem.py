import math


def build_poor_price(arr_data, arr_poor_price):
        arr_data_len = len(arr_data);
        arr_poor_price.append(0);
        for i in range (1, arr_data_len):
                arr_poor_price.append(arr_data[i] - arr_data[i-1])


def find_max_crossing_subarray(arr_poor_price, low, middle, hight):
        left_max = -math.inf
        low_index = middle;
        sum = 0
        for i in range (middle, low+1, -1):
                sum += arr_poor_price[i]
                if(sum > left_max):
                        left_max = sum
                        low_index = i
        right_max = -math.inf
        hight_index = middle
        sum = 0
        for i in range (middle+1, hight+1):
                sum += arr_poor_price[i]
                if(sum > right_max):
                        right_max = sum
                        hight_index = i
        return low_index, hight_index, (left_max + right_max)


def find_maximum_subarray(arr_poor_data, low, hight):
        if low == hight:
                return low, hight, arr_poor_data[low]
        else:
                middle = math.floor((low+hight)/2)
                left_low_index, left_hight_index, left_max_sum = find_maximum_subarray(arr_poor_data, low, middle)
                right_low_index, right_hight_index, right_max_sum = find_maximum_subarray(arr_poor_data, low, middle)
                cross_low_index, cross_hight_index, cross_max_sum = find_max_crossing_subarray(arr_poor_data, low, middle, hight)

                max_sum = max(left_max_sum, right_max_sum, cross_max_sum)
                if left_max_sum == max_sum:
                        return left_low_index, left_hight_index, left_max_sum
                elif right_max_sum == max_sum:
                        return right_low_index, right_hight_index, right_max_sum
                else:
                        return cross_low_index, cross_hight_index, cross_max_sum


if __name__ == '__main__':
        print('Maximum Subarray Problem')

        # create daily price
        text_data = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
        print("text_data: ", text_data)

        # build poor price
        arr_poor_price = []
        build_poor_price(text_data, arr_poor_price)
        print("arr_poor_price: ", arr_poor_price)

        # find_max_subarray
        low_index, hight_index, max_sum = find_maximum_subarray(arr_poor_price, 1, len(arr_poor_price)-1)
        # output result
        print("\nlow_index: ", low_index,"\nhight_index: ", hight_index,"\nmax_sum:", max_sum)
