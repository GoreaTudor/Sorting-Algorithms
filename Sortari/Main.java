public class Main {
	static void afisare(int v[], int n) {
		for(int i=0; i<n; i++)
			System.out.printf("%d ", v[i]);
		System.out.println();
	}
	
	static void swap_v(int v[], int i, int j) {
		int temp = v[i];
		v[i] = v[j];
		v[j] = temp;
	}
	
	static void init_v(int v[], int n, int lim) {
		int i;
		for(i=0; i<n; i++) {
			v[i] = (int)(Math.random() * lim);
			//System.out.printf("%d ", v[i]);
		}
	}
	
	
	static void Selection_Sort(int v[], int n) {
		int i, j;
		for(i=0; i<n-1; i++)
			for(j=i+1; j<n; j++)
				if(v[i] > v[j])
					swap_v(v, i, j);
	}
	
	static void Bubble_Sort(int v[], int n) {
		int ok, i;
		do {
			ok = 1;
			
			for(i=0; i<n-1; i++)
				if(v[i] > v[i+1]) {
					swap_v(v, i, i+1);
					ok = 0;
				}
		}while(ok == 0);
	}
	
	static void Cocktail_Sort(int v[], int n) {
		int ok, i;
		do {
			ok = 1;
			
			for(i=0; i<n-1; i++)
				if(v[i] > v[i+1]) {
					swap_v(v, i, i+1);
					ok = 0;
				}
			
			for(i=n-2; i>=0; i--)
				if(v[i] > v[i+1]) {
					swap_v(v, i, i+1);
					ok = 0;
				}
			
		}while(ok == 0);
	}
	
	static void Insertion_Sort(int v[], int n) {
		int i, j, temp;
		
		for(i=1; i<n; i++) {
			temp = v[i];
			
			j = i - 1;
			while(j>=0 && temp < v[j]){
				v[j+1] = v[j];
				j--;
			}
			v[j+1] = temp;
		}
	}
	
	static void Shell_Sort(int v[], int n) {
		int i, j, temp, gap;
		
		for(gap = n/2; gap>0; gap/=2) {
			for(i=gap; i<n; i++) {
				temp = v[i];
				
				j = i - gap;
				while(j>=0 && temp < v[j]){
					v[j+gap] = v[j];
					j -= gap;
				}
				v[j+gap] = temp;
			}
		}
	}
	
	static int[] Rank_Sort(int v[], int n) {
		int frecv[] = new int[n];
		int vect[] = new int[n];
		int i, j;
		
		for(i=0; i<n; i++)
			frecv[i] = 0;
		
		for(i=0; i<n; i++) {
			for(j=i+1; j<n; j++) {
				if(v[i] > v[j])
					frecv[i]++;
				else
					frecv[j]++;
			}
		}
		
		for(i=0; i<n; i++)
			vect[frecv[i]] = v[i];
		
		frecv = null;
		v = null;
		
		return vect;
	}
	
	
	static void Counting_Sort(int v[], int n, int lim) {
		int frecv[] = new int[lim];
		int i, j, k=0;
		
		for(i=0; i<lim; i++)
			frecv[i] = 0;
		
		for(i=0; i<n; i++)
			frecv[v[i]]++;
		
		for(i=0; i<lim; i++)
			for(j=0; j<frecv[i]; j++)
				v[k++] = i;
		
		frecv = null;
	}

	static int[] countSort(int v[], int n, int exp) {
		int vect[] = new int[n];
		int frecv[] = new int[10];
		int i, cif;
		
		for(i=0; i<10; i++) //init frecv
			frecv[i] = 0;
		
		for(i=0; i<n; i++) { //frecventa cifrei
			cif = (v[i] / exp) % 10;
			frecv[cif]++;
		}
		
		for(i=1; i<10; i++) //suma cumulativa pt pozitia actuala
			frecv[i] += frecv[i-1];
		
		for(i=n-1; i>=0; i--) { //crearea vectorului nou -- obligatoriu in ordine inversa
			cif = (v[i] / exp) % 10;
			frecv[cif]--;
			vect[frecv[cif]] = v[i];
		}
		
		frecv = null;
		v = null;
		
		return vect;
	}
	static int[] Radix_Sort(int v[], int n) {
		int exp, maxim, i;
		maxim = v[0];
		for(i=1; i<n; i++)
			if(maxim < v[i])
				maxim = v[i];
		
		for(exp=1; maxim / exp > 0; exp *= 10) {
			v = countSort(v, n, exp);
		}
		
		return v;
	}
	
	
	static void Merge(int v[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		int i, j, k;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for(i=0; i<n1; i++)
			L[i] = v[l + i];
		
		for(j=0; j<n2; j++)
			R[j] = v[m + 1 + j];
		
		i=0; j=0; k=l;
		
		while(i < n1 && j < n2) {
			if(L[i] <= R[j]) {
				v[k] = L[i];
				i++;
			}
			else {
				v[k] = R[j];
				j++;
			}
			k++;
		}
		
		while(i<n1) {
			v[k] = L[i];
			i++;
			k++;
		}
		
		while(j<n2) {
			v[k] = R[j];
			j++;
			k++;
		}
		
		L = null;
		R = null;
		
	}
	static void Merge_Sort(int v[], int l, int r) {
		if(l >= r)
			return;
		
		int m = (l + r) / 2;
		
		Merge_Sort(v, l, m);
		Merge_Sort(v, m+1, r);
		Merge(v, l, m, r);
	}
	
	static void Quick_Sort(int v[], int st, int dr) {
		int pivot = v[(st + dr) / 2];
		int i = st;
		int j = dr;
		
		while(i<=j) {
			while(v[i] < pivot) i++;
			while(pivot < v[j]) j--;
			
			if(i<=j) {
				swap_v(v, i, j);				
				
				i++;
				j--;
			}
		}
		
		if(st < j) Quick_Sort(v, st, j);
		if(i < dr) Quick_Sort(v, i, dr);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	public static void main(String[] args) {	
		int n=10, lim=50;
		int v[] = new int[n];
		
		System.out.println("Selection Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		Selection_Sort(v, n); afisare(v, n);
		System.out.println();
		
		System.out.println("Bubble Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		Bubble_Sort(v, n); afisare(v, n);
		System.out.println();
		
		System.out.println("Cocktail Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		Cocktail_Sort(v, n); afisare(v, n);
		System.out.println();
		
		System.out.println("Insertion Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		Insertion_Sort(v, n); afisare(v, n);
		System.out.println();
		
		System.out.println("Shell Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		Shell_Sort(v, n); afisare(v, n);
		System.out.println();
		
		System.out.println("Rank Sort (N x N)");
		init_v(v, n, lim); afisare(v, n);
		v = Rank_Sort(v, n); afisare(v, n);
		System.out.println();
		
		
		System.out.println();
		
		
		System.out.println("Counting Sort (N)");
		init_v(v, n, lim); afisare(v, n);
		Counting_Sort(v, n, lim); afisare(v, n);
		System.out.println();
		
		System.out.println("Radix Sort (N)");
		init_v(v, n, lim); afisare(v, n);
		v = Radix_Sort(v, n); afisare(v, n);
		System.out.println();
		
		
		System.out.println();
		
		
		System.out.println("Merge Sort (N x log N)");
		init_v(v, n, lim); afisare(v, n);
		Merge_Sort(v, 0, n-1); afisare(v, n);
		System.out.println();
		
		System.out.println("Quick Sort (N x log N)");
		init_v(v, n, lim); afisare(v, n);
		Quick_Sort(v, 0, n-1); afisare(v, n);
		System.out.println();
		
		v = null;
	}
}
