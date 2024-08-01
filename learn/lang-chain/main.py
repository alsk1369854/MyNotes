from src import run

import uuid

def main() -> None:
   run()
   print( str(uuid.uuid4()))


if __name__ == "__main__":
    main()
