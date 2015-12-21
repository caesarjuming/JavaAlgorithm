package com;

/**
 * Created by juming.wang on 2015/12/21.
 */
public class KMP {



    public void search(String original, String find, int next[]) {
        int j = 0;
        for (int i = 0; i < original.length(); i++) {
            while (j > 0 && original.charAt(i) != find.charAt(j))
                j = next[j];
            if (original.charAt(i) == find.charAt(j))
                j++;
            if (j == find.length()) {
                System.out.println("find at position " + (i - j));
                System.out.println(original.subSequence(i - j + 1, i + 1));
                j = next[j];
            }
        }
    }
    public int[] next(String b)
    {
        int len=b.length();
        int j=0;

        int next[]=new int[len+1];//next��ʾ����Ϊi���ַ���ǰ׺�ͺ�׺����������֣���1��ʼ
        next[0]=next[1]=0;

        for(int i=1;i<len;i++)//i��ʾ�ַ������±꣬��0��ʼ
        {//j��ÿ��ѭ����ʼ����ʾnext[i]��ֵ��ͬʱҲ��ʾ��Ҫ�Ƚϵ���һ��λ��
            while(j>0&&b.charAt(i)!=b.charAt(j))j=next[j];
            if(b.charAt(i)==b.charAt(j))j++;
            next[i+1]=j;
        }

        return next;
    }
}
