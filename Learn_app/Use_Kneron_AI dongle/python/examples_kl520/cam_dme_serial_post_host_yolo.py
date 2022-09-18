"""
This is the example for dme ssd fd single test.
"""
from common import constants, kdp_wrapper
from common.pre_post_process.yolo.yolo_postprocess import yolo_postprocess_

def user_test_single_dme(dev_idx, loop):
    """Test single dme."""
    model_file = "../input_models/KL520/tiny_yolo_v3/models_520.nef"
    model_id = constants.ModelType.TINY_YOLO_V3_224_224_3.value
    image_cfg = (constants.IMAGE_FORMAT_SUB128 | constants.NPU_FORMAT_RGB565 |
                    constants.IMAGE_FORMAT_RAW_OUTPUT)

    # Load model into Kneron device.
    dme_config = kdp_wrapper.init_dme_config(model_id, 2, image_cfg)
    ret = kdp_wrapper.dme_load_model(dev_idx, model_file, dme_config)
    if ret == -1:
        return -1

    image_source_h = 480
    image_source_w = 640
    image_size = image_source_w * image_source_h * 2
    frames = []
    app_id = 0 # if app_id is 0, output raw data for kdp_wrapper.kdp_dme_inference

    # the parameters for postprocess
    anchor_path = './common/pre_post_process/yolo/models/anchors_tiny_v3.txt'
    class_path = './common/class_lists/coco_name_lists'
    model_input_shape = (224, 224)
    score_thres = 0.2
    nms_thres = 0.45
    keep_aspect_ratio = True

    # Setup video capture device.
    capture = kdp_wrapper.setup_capture(0, image_source_w, image_source_h)
    if capture is None:
        return -1

    # Perform inference and draw result for each capture frame.
    while loop:
        raw_res = kdp_wrapper.dme_inference(
            dev_idx, 0, capture, model_id, app_id, image_size, frames)
        if raw_res is None:
            return -1

        dets = yolo_postprocess_(raw_res, anchor_path, class_path, image_source_h, image_source_w,
                                 model_input_shape, score_thres, nms_thres, keep_aspect_ratio)

        kdp_wrapper.draw_capture_result(dev_idx, dets, frames, "yolo")
        loop -= 1

    return 0

def user_test(dev_idx, _user_id):
    # DME test
    ret = user_test_single_dme(dev_idx, 1000)
    kdp_wrapper.end_det(dev_idx)
    return ret
