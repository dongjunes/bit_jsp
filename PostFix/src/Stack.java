
public class Stack {

	private int data[] = new int[5];
	private int index = 0;

	public void pushData(int data) {
		if (index == this.data.length) {
			System.out.println("Stack was full");
		} else {
			this.data[index++] = data;
			System.out.println("데이터를 삽입하였습니다.(num)");
		}
	}

	public int popData() {
		if (index == -1) {
			System.out.println("Stack was empty");
		} else {
			int returnData = this.data[--index];
			this.data[index] = 0;
			return returnData;
		}

		return 0;
	}

	public void printStack() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}

	}

	public int getIndex() {
		return this.index;
	}

}
