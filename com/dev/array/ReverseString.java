class ReverseString{
  public static String reverseString(String str){
    char[] cArr = str.toCharArray();
    String rev = "";
    for(int i = cArr.length; i >= 0; i--){
      rev += cArr[i];
    }
    return rev;
  }
}
