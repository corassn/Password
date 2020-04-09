package UMT;

import java.util.*;
import java.lang.*;

public class Password
{   static int isStrong(String s)
    {
        int ins=0, del=0, rep=0; //these are the changes that should take place if the password is not strong enough
        int lower=1, upper=1, digit=1; // the missing characters
        int repeat=0, max_repeat=0, j=1; //repeat - variable for how many times there's a repetition
        //max_repeat - variable for the longest string of repetitions
        //j - counter for a string of repetition
        int i;
        int len=s.length(); // length of the password

        char[] ch = s.toCharArray();

        for( char c : ch) { // here we verify whether there is at least one lowercase/uppercase/digit char
            if (Character.isLowerCase(c))
                lower = 0;
            if (Character.isUpperCase(c))
                upper = 0;
            if (Character.isDigit(c))
                digit = 0;
        } //if there is at least one lower/upper/digit, then there's no need for change in password in that case

        for(i=0; i<len-2; i++){ //here we verify the repetitions
            if (len < 6) { //if the length is too small
                if (ch[i] == ch[i + 1] && ch[i] == ch[i + 2]) {
                    repeat++; //if the next 2 chars are equal, we add 1
                    i++; //every 2 chars we can insert a char to get the ideal length
                }
            } else if (ch[i] == ch[i + 1] && ch[i] == ch[i + 2]) { //if the length is good or too big
                repeat++; // we take the repeat the same - can the delete or replace action
                i = i + 2; //we jump over the repetition sequence of 3 chars
            }
            if(ch[i]==ch[i+1])
                j++;
            else {
                if(max_repeat<j)
                    max_repeat=j; //here we measure the longest sequence of repetition
                j=1;
            }
        }
        System.out.println(repeat);
        System.out.println(len);
        System.out.println(max_repeat);
        // here, we verify the length of the string and do the calculations to find the minimum necessary changes
        if(len<6){ //if length too small
            ins=Integer.max(6-len,lower + upper + digit); // we do the maximum between the missing chars
        } else if(len>20) //if length too big
            if(max_repeat-2>len-20||max_repeat<=2) //for a sequence of repetition bigger than the chars that need to be deleted
                // /or repetition doesn't exist
                del=Integer.max(len-20+repeat,len-20+lower + upper + digit); //maximum again. repetition can include the missing characters
            else { //if the sequence of reptition is smaller than the chars that need to be deleted
                repeat-=len-20;
                if(repeat<0)
                    repeat=0;
                del=Integer.max(len-20+repeat,len-20+lower + upper + digit);
            }
        else rep = Integer.max(repeat, lower + upper + digit); // take the maximum. repetition can include the missing characters(replace)


        return ins+del+rep; //if there is no need for change, it will return 0
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println(isStrong(s.nextLine()));
    }
}
