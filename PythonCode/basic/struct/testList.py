import unittest  # The test framework


class TestList(unittest.TestCase):
    def testCreateList(self):
        # init
        basicList = ["ming", "mark", "han"]

        # assert
        self.assertEqual(basicList, ["ming", "mark", "han"])
        self.assertEqual(len(basicList), 3)

    def testListCopy(self):
        # iniy
        basicList = ["ming", "mark", "han"]
        copyList = None

        # operate
        copyList = basicList.copy()
        copyList[0] = "JJ"

        # assert
        self.assertEqual(basicList, ["ming", "mark", "han"])
        self.assertEqual(copyList, ["JJ", "mark", "han"])

    def testItemContain(self):
        # init
        basicList = ["ming", "mark", "han"]
        targetItem1 = "ming"
        targetItem2 = "JJ"

        # assert
        self.assertEqual((targetItem1 in basicList), True)
        self.assertEqual((targetItem2 in basicList), False)

    def testAddItem(self):
        # init
        basicList = ["ming", "mark", "han"]
        newItem = "JJ"

        # operate
        basicList.append(newItem)

        # assert
        self.assertEqual(basicList, ["ming", "mark", "han", "JJ"])

    def testInsertItem(self):
        # init
        basicList = ["ming", "mark", "han"]
        insertIndex = 0
        insertItem = "JJ"

        # operate
        basicList.insert(insertIndex, insertItem)

        # assert
        self.assertEqual(basicList, ["JJ", "ming", "mark", "han"])

    def testListExtend(self):
        # init
        basicList = ["ming", "mark", "han"]
        extendList = ["JJ", "BB"]

        # operate
        basicList.extend(extendList)

        # assert
        self.assertEqual(basicList, ["ming", "mark", "han", "JJ", "BB"])

    def testPop(self):
        # init
        basicList = ["ming", "mark", "han"]

        # operate
        item = basicList.pop()

        # assert
        self.assertEqual(item, "han")
        self.assertEqual(basicList, ["ming", "mark"])

    def testDeleteItem(self):
        # init
        basicList = ["ming", "mark", "han"]

        # operate
        del basicList[2]

        # assert
        self.assertEqual(basicList, ["ming", "mark"])

    def testDelete(self):
        # init
        basicList = ["ming", "mark", "han"]

        # operate
        del basicList


if __name__ == "__main__":
    unittest.main()
