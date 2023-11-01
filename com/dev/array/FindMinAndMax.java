/*
class Pair{
  long first;
  long second;
  public Pair(long f, long s){
    this.first = f;
    this.second = s;
  }
}
*/


class FindMinAndMax{
  static Pair getMinMax(long a[], long n){
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;
    Pair p = new Pair(min, max);
    for(int i = 0; i < n; i++){
      if(a[i] > max){
        max = a[i];
      }
      if(a[i] < min){
        min = a[i];
      }
      p = new Pair(min, max);
    }
    return p;
  }
}
      
