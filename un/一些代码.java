

public class Sort {
	不稳定，nlogn
	public class QuickSort {
		public void sort(int[] nums) {
			int length = nums.length;
			sort(nums, 0, length - 1);
		}

		private void sort(int[] nums, int lo, int hi) {
			if (lo >= hi) return;
			int j = partition(nums, lo, hi);
			sort(nums, lo, j - 1);
			sort(nums, j + 1, hi);
		}

		private int partition(int[] nums, int lo, int hi) {
			int anchor = nums[lo];
			int i = lo, j = hi + 1;

			while (true) {
				while (nums[++i] <= anchor && i < hi);
				while (nums[--j] >= anchor && j > lo);
				if (i >= j) break;
				swap(nums, i, j);
			}
			swap(nums, lo, j);
			return j;
		}
	}

	public class MergeSort {
		private int[] aux;
		public void sort(int[] nums) {
			aux = new int[nums.length];
			sort(nums, 0, nums.length - 1);
		}

		private void sort(int[] nums, int lo, int hi) {
			if (lo >= hi) return ;

			int mid = lo + (hi - lo) / 2;
			sort(nums, lo, mid);
			sort(nums, mid+1, hi);
			merge(nums, lo, mid, hi);
		}

		private void merge(int[] nums, int lo, int mid, int hi) {
			for (int i = lo; i <= hi; i++) {
				aux[i] = nums[i];
			}

			int j = lo, k = mid + 1;

			for (int i = lo; i <= hi; i++) {
				if (j > mid) nums[i] = aux[k++];
				else if (k > hi) nums[i] = aux[j++];
				else if (aux[j]<= aux[k]) nums[i] = aux[j++];
				else nums[i] = aux[k++];
			}
		}
	}

	public class BubbleSort {
		public void sort(int[] nums) {
			for (int i = nums.length - 1; i > 0 i--) {
				for (int j = 0; j < i; j++) {
					if (nums[j] > nums[j+1]) {
						swap(nums, i, j);
					}
				}
			}
		}
	}

	public class InsertSort {
		public void sort(int[] nums) {
			for (int i = 1; i < nums.length; i++) {
				for (int j = i ; j > 0 ; j--) {
					if (nums[j] < nums[j-1]) {
						swap(nums, j, j-1);
					}
				}
			}
		}
	}

	public class SelectSort {
		public void sort(int[] nums) {
			
			for (int i = 0; i <nums.length - 1; i++) {
				int minP = i;
				for (int j = i; j < nums.length; j++) {
					if (nums[minP] > nums[j]) {
						minP = j;
					}
				}
				swap(nums, minP, j);
			}
		}
	}

	public class HeapSort {
		public void sort(int[] nums) {
			for (int i = 0; i < nums.length; i++) {
				createHeap(nums, i);
			}
			int heapSize = nums.length;
			while (--heapSize > 0) {
				swap(nums, 0, heapSize);
				heapify(nums, 0, heapSize);
			}
		}

		private void createHeap(int[] nums, int index) {
			int parent = (index - 1) / 2;
			while (nums[index] > nums[parent]) {
				swap(nums, index, parent);
				index = parent;
			}
		}

		private void heapify(int[] nums, int index, int heapSize) {
			int left = index * 2 + 1;
			while (left < heapSize) {
				int largest = ((left + 1) < heapSize && nums[left + 1] > nums[left]) ? left + 1 : left;
				largest = nums[index] > nums[largest] ? index : largest;
				if (index == largest) break;
				swap(nums, largest, index);
				index = largest;
				left = index * 2 + 1;

			}
		}
	}
}

public class Traversal {
	public class Recursive {
		public void traversal(TreeNode treeNode) {
			if (treeNode == null) return;
			System.out.println(treeNode.val);
			traversal(treeNode.left);
			traversal(treeNode.right);
		}
	}

	public class Iterative(TreeNode treeNode) {
		public void traversal(TreeNode treeNode) {
			Stack<TreeNode> stack = new Stack<>();
			while (treeNode != null && !stack.isEmpty()) {
				while (treeNode != null) {
					System.out.println(treeNode.val);
					stack.push(treeNode);
					treeNode = treeNode.left;
				}
				if (!stack.isEmpty()) {
					treeNode = stack.pop();
					treeNode = treeNode.right;
				}
			}
		}
	}
}