#include <stdio.h>

int main (){
	int n;
	scanf ("%d", &n);
	
	int values[n];
	for (int i=0; i<n; i++){
		scanf("%d", &values[i]);
	}
	int k, counter=0, output;
	scanf ("%d", &k);
	
	bool flag = false;
	if (k==1) {
		for (int i=0; i<n; i++){
			if (flag) {
				printf (" ");
			}
			else flag = true;
			printf ("%d", values[i]);
		}
	}
	
	flag = false;
	for (int i=0; i<n-k+1; i++){
		counter = 1;
		output=values[i];
		for (int j=i+1; j<n; j++){
			if (output<values[j]) output = values[j];
			counter++;
			if (counter==k){
				if (flag) {
					printf (" ");
				}
				else flag = true;
				printf ("%d", output);
				break;
			}
		}
	}
	printf ("\n");
	return 0;	
}
