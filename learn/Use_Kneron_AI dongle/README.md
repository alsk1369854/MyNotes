## Install Host Lib Python Package

### Prerequisites

**General**:

* Python 3.8  
   * Some system may have python 2.7 as their default python. In that case, you need to use replace 'python' with 'python3' , 'pip' with 'pip3'  in the following examples.  
* opencv-python (Install using `pip install opencv-python`)
   * on raspberry pi,  using `pip3 install opencv-python==3.4.6.27` and if you have other version of opencv-python installed, please run `pip3 uninstall opencv-python`to uninstall them first
* matplotlib and pillow (Install using `pip install matplotlib`)

**Linux**:

* **For Ubuntu, 18.04 is verified**.
* libusb (Ubuntu install using `apt install libusb-1.0-0-dev`)

**Windows**:

* WinUSB (Install after plugging in the dongle and run [Zadig](https://zadig.akeo.ie/))

![](docs/zadig.jpg)

* MINGW64/MSYS or PowerShell
    * MSYS
    ```bash
        pacman -S python3-pip  # restart MSYS after installation
        pacman -S mingw-w64-x86_64-opencv
        pacman -S mingw-w64-x86_64-python-numpy
    ```

    * PowerShell: run as administrator

**Mac OS**:

* **Mac OS should be later than 10.15.6**.
* libusb (Install with [Homebrew](https://brew.sh/) using `brew install libusb`)

### Installation

Simply run the following command but replace the item within `<>` .

```bash
pip install python/packages/kdp_host_api-<version>_<os_version>_-py3-none-any.whl
```

`os_version` should be `win`, `mac`, `Raspbian` or`linux`.

Please check the `python/packages` folder for the `version` available.

Extra steps for MSYS
```bash
python -m site # check sys.path
pip install -t <site-packages_relative_path_in_sys.path> python/packages/kdp_host_api-<version>_<os_version>_-py3-none-any.whl
#example: pip install python/packages/kdp_host_api-1.0.0_win_-py3-none-any.whl
```