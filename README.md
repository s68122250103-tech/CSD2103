# รายงานแบบฝึกหัดการออกแบบอัลกอริทึมแบบเวียนเกิดและการวิเคราะห์ Big-O

---

## ข้อที่ 1: การกลับลำดับสตริง (String Reversal)

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Recursive Algorithm (`reverseRecursive`)**  
  ใช้นิยามเวียนเกิดโดยดึงตัวอักษรตัวสุดท้ายของสตริงปัจจุบันออกมาไว้หน้าสุด แล้วนำไปต่อกับผลลัพธ์ของการเรียกตัวเองซ้ำ (Recursive Call) ด้วยสตริงส่วนที่เหลือ (ตั้งแต่ดัชนี 0 ถึงก่อนตัวสุดท้าย) ทำซ้ำไปเรื่อยๆ จนกว่ากรณีพื้นฐาน (Base Case) จะทำงาน เมื่อความยาวสตริงเหลือ `<= 1`
* **วิธีที่ 2: Iterative Algorithm (`reverseIterative`)**  
  ใช้วงวน (Loop) อ่านตัวอักษรย้อนกลับตั้งแต่นิพจน์สุดท้ายย้อนมาจนถึงนิพจน์แรก แล้วนำตัวอักษรแต่ละตัวไปต่อลงในวัตถุ `StringBuilder` จนครบทุกตัวอักษร

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm reverseRecursive(s):
    if s is null or s.length <= 1 then
        return s
    return s.charAt(s.length - 1) + reverseRecursive(s.substring(0, s.length - 1))

Algorithm reverseIterative(s):
    if s is null or s.length <= 1 then
        return s
    sb = new StringBuilder()
    for i = s.length - 1 down to 0 do
        sb.append(s.charAt(i))
    return sb.toString()
```
### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
public class StringReversal {

    public static String reverseRecursive(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return s.charAt(s.length() - 1) + reverseRecursive(s.substring(0, s.length() - 1));
    }

    public static String reverseIterative(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `"pots&pans"`
* **Output**: `"snap&stop"`

---

### 5. การวิเคราะห์ Time Complexity

* **Recursive Algorithm**: `O(n^2)`
มีการเรียกตัวเองซ้ำ `n` ครั้ง และในแต่ละชั้นมีการใช้ `substring()` ร่วมกับการเชื่อมสตริงด้วยเครื่องหมาย `+` ซึ่งทำการคัดลอกตัวอักษรขนาด `k` ตัวอักษร
* **Iterative Algorithm**: `O(n)`
ทำงานผ่านลูปวนอ่านตัวอักษร `n` ครั้ง โดยการ append ลง `StringBuilder` ใช้เวลาคงที่ `O(1)`

---

### 6. การวิเคราะห์ Space Complexity

* **Recursive Algorithm**: `O(n^2)`
ใช้พื้นที่ Call Stack ตามความลึก `n` และสร้างวัตถุสตริงใหม่สะสมใน Heap Memory ในทุกๆ ชั้นของการเรียก
* **Iterative Algorithm**: `O(n)`
ใช้พื้นที่หน่วยความจำคงที่สำหรับ `StringBuilder` ขนาดเท่ากับ `n` ตัวอักษรเท่านั้น

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Recursive Algorithm**
* **ข้อดี**: โค้ดสั้น กระชับ อ่านเข้าใจง่ายเชิงคณิตศาสตร์
* **ข้อจำกัด**: ประสิทธิภาพต่ำ เปลืองหน่วยความจำสูง และเสี่ยงเกิด `StackOverflowError` เมื่อข้อมูลมีขนาดใหญ่


* **Iterative Algorithm**
* **ข้อดี**: ประมวลผลเร็ว ประหยัดทรัพยากร ทำงานกับสตริงขนาดใหญ่มากได้ปลอดภัย
* **ข้อจำกัด**: ใช้บรรทัดโค้ดมากกว่าเล็กน้อย



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* **Iterative Algorithm** เหมาะสมที่สุดสำหรับระบบงานจริงทุกกรณี เนื่องจากประหยัดทรัพยากรหน่วยความจำและรันได้อย่างปลอดภัย
* **Recursive Algorithm** เหมาะสมเฉพาะสำหรับการศึกษาเรื่อง Recursion หรือใช้งานกับสตริงขนาดเล็กมากๆ (`n < 100`) เท่านั้น

```

```

---

## ข้อที่ 2: การตรวจสอบ Palindrome

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Reverse and Compare (`isPalindromeByReverse`)**  
  ทำความสะอาดสตริงโดยแปลงเป็นตัวพิมพ์เล็กและตัดอักขระพิเศษออก จากนั้นสร้างสตริงตัวใหม่ที่ย้อนกลับลำดับ แล้วใช้ `.equals()` ตรวจสอบว่าเหมือนกับสตริงเดิมหรือไม่
* **วิธีที่ 2: Recursive Two-Pointer (`isPalindromeRecursive`)**  
  ทำความสะอาดสตริง แล้วใช้ตัวชี้ตำแหน่งซ้าย (`left`) และขวา (`right`) เปรียบเทียบตัวอักษรสองฝั่ง หากตรงกันจะเรียกตัวเองเพื่อบีบขอบเขตเข้าหาศูนย์กลางทีละ 1 ตำแหน่ง

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm isPalindromeByReverse(s):
    cleaned = cleanString(s)
    reversed = reverse(cleaned)
    return cleaned.equals(reversed)

Algorithm isPalindromeRecursive(s, left, right):
    if left >= right then
        return true
    if s.charAt(left) != s.charAt(right) then
        return false
    return isPalindromeRecursive(s, left + 1, right - 1)
```

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
public class PalindromeChecker {

    public static String cleanString(String s) {
        if (s == null) return "";
        return s.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    public static boolean isPalindromeByReverse(String s) {
        String cleaned = cleanString(s);
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    public static boolean isPalindromeRecursive(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    public static boolean isPalindromeRecursiveWrapper(String s) {
        String cleaned = cleanString(s);
        return isPalindromeRecursive(cleaned, 0, cleaned.length() - 1);
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `"A man, a plan, a canal: Panama"`
* **Output**: `true`

---

### 5. การวิเคราะห์ Time Complexity

* **Reverse and Compare**: `O(n)`
ต้องประมวลผลกลับลำดับและเปรียบเทียบสตริงจนครบความยาว `n` ตัวอักษรเสมอ
* **Recursive Two-Pointer**:
* **Best-Case**: `O(1)` เมื่อตัวอักษรตัวแรกและตัวสุดท้ายไม่ตรงกัน จะคืนค่า `false` ทันที
* **Worst-Case**: `O(n)` เมื่อเป็น Palindrome จะต้องตรวจจนถึงตรงกลาง (`n / 2` ครั้ง)



---

### 6. การวิเคราะห์ Space Complexity

* **Reverse and Compare**: `O(n)`
ต้องสร้างวัตถุสตริงใหม่ขนาด `n` เพื่อใช้เปรียบเทียบ
* **Recursive Two-Pointer**: `O(n)`
ใช้หน่วยความจำ Call Stack ตามความลึกสูงสุด `n / 2` ชั้น

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Reverse and Compare**
* **ข้อดี**: เขียนโค้ดกระชับ อ่านเข้าใจง่าย ใช้ไลบรารีมาตรฐานของภาษา
* **ข้อจำกัด**: หลุดออกจากลูป (Early Exit)ไม่ได้ ต้องสร้างสตริงย้อนกลับจนเสร็จสมบูรณ์ก่อนเสมอ ทำให้เปลืองหน่วยความจำ Heap Memory


* **Recursive Two-Pointer**
* **ข้อดี**: มีความสามารถในการหยุดประมวลผลทันที (Early Exit) เมื่อพบอักขระที่ไม่ตรงกัน
* **ข้อจำกัด**: ใช้พื้นที่ Call Stack สะสมตามความยาวของสตริง



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* **Recursive Two-Pointer** เหมาะสมกว่าในกรณีทั่วไป เนื่องจากสามารถตอบกลับได้ทันทีเมื่อเจอตัวอักษรที่ไม่ตรงกัน ทำให้มี Best-Case Time Complexity เป็น `O(1)`
* **Reverse and Compare** เหมาะสมสำหรับการเขียนโค้ดสั้นๆ เน้นความรวดเร็วในการพัฒนาเมื่อข้อความมีความยาวไม่มาก

```

```

---

## ข้อที่ 3: การเปรียบเทียบจำนวนสระและพยัญชนะ

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Recursive Counting (`hasMoreVowelsRecursive`)**  
  ใช้อัลกอริทึมเวียนเกิดตรวจสอบอักขระทีละดัชนี พร้อมส่งค่าตัวสะสมจำนวนสระและพยัญชนะต่อไปยังการเรียกชั้นถัดไป เมื่อถึงจุดสิ้นสุดของสตริงจะนำค่าที่นับได้มาเปรียบเทียบกันว่าสระมากกว่าหรือไม่
* **วิธีที่ 2: Iterative Counting (`hasMoreVowelsIterative`)**  
  ใช้วงวน `for` อ่านอักขระตั้งแต่นิพจน์แรกจนถึงตัวสุดท้าย แล้วอัปเดตตัวแปรนับสระหรือพยัญชนะในหน่วยความจำโดยละเว้นตัวเลขและสัญลักษณ์พิเศษ

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm countRecursive(s, index, vowels, consonants):
    if index == s.length then
        return vowels > consonants
    ch = s.charAt(index)
    if isVowel(ch) then vowels++
    else if isConsonant(ch) then consonants++
    return countRecursive(s, index + 1, vowels, consonants)

Algorithm hasMoreVowelsIterative(s):
    vowels = 0, consonants = 0
    for i = 0 to s.length - 1 do
        ch = s.charAt(i)
        if isVowel(ch) then vowels++
        else if isConsonant(ch) then consonants++
    return vowels > consonants
```


### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
public class VowelConsonantComparer {

    private static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }

    private static boolean isConsonant(char ch) {
        return ch >= 'a' && ch <= 'z' && !isVowel(ch);
    }

    public static boolean hasMoreVowelsRecursive(String s) {
        if (s == null) return false;
        return countRecursive(s.toLowerCase(), 0, 0, 0);
    }

    private static boolean countRecursive(String s, int index, int vowels, int consonants) {
        if (index == s.length()) {
            return vowels > consonants;
        }
        char ch = s.charAt(index);
        if (isVowel(ch)) vowels++;
        else if (isConsonant(ch)) consonants++;
        return countRecursive(s, index + 1, vowels, consonants);
    }

    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null) return false;
        String lower = s.toLowerCase();
        int vowels = 0, consonants = 0;
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (isVowel(ch)) vowels++;
            else if (isConsonant(ch)) consonants++;
        }
        return vowels > consonants;
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `"education"`
* **Output**: `true` (สระ 5 ตัว: e, u, a, t, i, o -> e, u, a, i, o / พยัญชนะ 4 ตัว: d, c, t, n)

---

### 5. การวิเคราะห์ Time Complexity

* **Recursive Counting**: `O(n)`
ทำการเรียกตัวเองซ้ำจนครบทุกดัชนี $n$ ตัวอักษร
* **Iterative Counting**: `O(n)`
วนลูปอ่านประมวลผลตัวอักษรทีละตัวจนครบ $n$ ตัวอักษร

---

### 6. การวิเคราะห์ Space Complexity

* **Recursive Counting**: `O(n)`
ต้องสร้าง Call Stack ตามความยาว $n$ ของสตริง
* **Iterative Counting**: `O(1)`
ใช้ตัวแปรนับจำนวนคงที่ (`vowels`, `consonants`) ปริมาณหน่วยความจำไม่เพิ่มขึ้นตามขนาดข้อมูล

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Recursive Counting**
* **ข้อดี**: ใช้โครงสร้างการทำงานแบบเชิงฟังก์ชัน (Functional Style) ไม่มีตัวแปรแปรผันภายนอก
* **ข้อจำกัด**: เปลืองหน่วยความจำ Stack Frame และเสี่ยงเกิด `StackOverflowError` บนข้อความขนาดใหญ่


* **Iterative Counting**
* **ข้อดี**: ประหยัดหน่วยความจำมากที่สุด (`O(1)`) ปลอดภัย ทำงานได้กับข้อความทุกขนาด
* **ข้อจำกัด**: ต้องเขียนตัวแปรสะสมค่าภายในลูป



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* **Iterative Counting** เหมาะสมที่สุดสำหรับการใช้งานจริงในทุกระบบ เนื่องจากใช้พื้นที่หน่วยความจำคงที่ (`O(1)`) และไม่มีข้อจำกัดเรื่องขนาดของสตริง

```

```
---

## ข้อที่ 4: การจัดกลุ่มจำนวนคู่และจำนวนคี่

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Iterative Two-Pointer (`rearrangeTwoPointer`)**  
  ใช้ตัวชี้สองตัว (`left` อยู่ซ้ายสุด และ `right` อยู่ขวาสุด) ขยับเข้าหากัน หากซ้ายเป็นเลขคู่จะขยับไปขวา หากขวาเป็นเลขคี่จะขยับไปซ้าย แต่หากซ้ายเป็นคี่และขวาเป็นคู่ จะทำการสลับค่า (Swap) กันทันที ทำแบบ In-place บนอาร์เรย์เดิม
* **วิธีที่ 2: Extra Array (`rearrangeExtraArray`)**  
  สร้างอาร์เรย์ใหม่ขนาดเท่าเดิม วนลูปคัดลอกเลขคู่ใส่ลงไปก่อน แล้ววนลูปอีกรอบเพื่อคัดลอกเลขคี่ใส่ต่อท้าย

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm rearrangeTwoPointer(A):
    left = 0, right = A.length - 1
    while left < right do
        while left < right and A[left] % 2 == 0 do left++
        while left < right and A[right] % 2 != 0 do right--
        if left < right then
            swap(A, left, right)
            left++; right--

Algorithm rearrangeExtraArray(A):
    result = new Array of size A.length
    idx = 0
    for num in A do
        if num % 2 == 0 then result[idx++] = num
    for num in A do
        if num % 2 != 0 then result[idx++] = num
    return result


```

---

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
public class EvenOddRearranger {

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void rearrangeTwoPointer(int[] a) {
        if (a == null || a.length <= 1) return;
        int left = 0, right = a.length - 1;
        while (left < right) {
            while (left < right && a[left] % 2 == 0) left++;
            while (left < right && a[right] % 2 != 0) right--;
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    public static int[] rearrangeExtraArray(int[] a) {
        if (a == null) return new int[0];
        int[] result = new int[a.length];
        int idx = 0;
        for (int num : a) {
            if (num % 2 == 0) result[idx++] = num;
        }
        for (int num : a) {
            if (num % 2 != 0) result[idx++] = num;
        }
        return result;
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `[7, 2, 9, 4, 1, 6, 3, 8]`
* **Output (Two-Pointer)**: `[8, 2, 6, 4, 1, 9, 3, 7]` *(กลุ่มคู่รวมอยู่ซ้าย คี่อยู่ขวา)*
* **Output (Extra Array)**: `[2, 4, 6, 8, 7, 9, 1, 3]` *(รักษาลำดับเดิม)*

---

### 5. การวิเคราะห์ Time Complexity

* **Iterative Two-Pointer**: `O(n)`
ตัวชี้ `left` และ `right` เลื่อนผ่านข้อมูลรวมกันเป็นระยะทางเท่ากับขนาดอาร์เรย์ $n$ พอดี
* **Extra Array**: `O(n)`
วนลูปอ่านข้อมูล 2 รอบ ($2n$ ครั้ง) ซึ่งสรุปประสิทธิภาพได้เป็น $O(n)$

---

### 6. การวิเคราะห์ Space Complexity

* **Iterative Two-Pointer**: `O(1)`
ทำงานแบบ In-place บนอาร์เรย์เดิมโดยใช้เพียงตัวแปรดัชนีเพิ่มเล็กน้อย
* **Extra Array**: `O(n)`
ต้องสร้างอาร์เรย์ใหม่ขนาดเท่ากับ $n$ ในการเก็บผลลัพธ์

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Iterative Two-Pointer**
* **ข้อดี**: ประหยัดหน่วยความจำมากที่สุด (`O(1)`) ทำงานได้รวดเร็ว
* **ข้อจำกัด**: **Unstable** (ไม่คงลำดับเดิมของตัวเลขไว้)


* **Extra Array**
* **ข้อดี**: **Stable** (รักษาลำดับเดิมของตัวเลขคู่และคี่เอาไว้ได้)
* **ข้อจำกัด**: ใช้พื้นที่หน่วยความจำเพิ่มขึ้นเป็นสองเท่า (`O(n)`)



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* เลือก **Iterative Two-Pointer** เมื่อเน้นประสิทธิภาพและประหยัดหน่วยความจำ
* เลือก **Extra Array** เมื่อระบบมีความจำเป็นต้องรักษาลำดับเดิมของข้อมูลเอาไว้ (Stable Rearrangement)

```

```
---

## ข้อที่ 5: การแบ่งอาร์เรย์ตามค่า k

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Iterative Partition (`partitionIterative`)**  
  ใช้ตัวชี้ดัชนี `i` คอยแบ่งขอบเขต วนลูปตรวจสอบสมาชิกทีละตัว หากค่า `<= k` จะทำการสลับค่า (Swap) มาไว้ที่ตำแหน่ง `i` แล้วขยับ `i` ไปข้างหน้า 1 ตำแหน่ง ทำงานแบบ In-place บนอาร์เรย์เดิม (แนวคิดเดียวกับ Partition ใน Quick Sort)
* **วิธีที่ 2: Sorting-Based (`partitionBySorting`)**  
  ใช้อัลกอริทึมการเรียงลำดับ (Sorting) เรียงสมาชิกทั้งอาร์เรย์จากน้อยไปมาก เพื่อให้ค่าทั้งหมดที่น้อยกว่าหรือเท่ากับ `k` ไปกองอยู่ฝั่งซ้ายของอาร์เรย์โดยอัตโนมัติ

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm partitionIterative(A, k):
    i = 0
    for j = 0 to A.length - 1 do
        if A[j] <= k then
            swap(A, i, j)
            i++

Algorithm partitionBySorting(A, k):
    Arrays.sort(A)
```

---

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
import java.util.Arrays;

public class PartitionByK {

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void partitionIterative(int[] a, int k) {
        if (a == null || a.length <= 1) return;
        int i = 0;
        for (int j = 0; j < a.length; j++) {
            if (a[j] <= k) {
                swap(a, i, j);
                i++;
            }
        }
    }

    public static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length <= 1) return;
        Arrays.sort(a);
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `A = [12, 4, 7, 15, 3, 10, 8]`, `k = 8`
* **Output (Iterative Partition)**: `[4, 7, 3, 8, 15, 10, 12]` *(ทุกตัวที่ <= 8 อยู่ซ้ายมือ)*
* **Output (Sorting-Based)**: `[3, 4, 7, 8, 10, 12, 15]` *(เรียงลำดับสมบูรณ์)*

---

### 5. การวิเคราะห์ Time Complexity

* **Iterative Partition**: `O(n)`
วนลูปอ่านข้อมูลและตรวจสอบเพียงรอบเดียว $n$ ครั้ง
* **Sorting-Based**: `O(n log n)`
ใช้อัลกอริทึมการเรียงลำดับข้อมูลทั้งอาร์เรย์ ซึ่งใช้เวลามากกว่ากรณีปกติ

---

### 6. การวิเคราะห์ Space Complexity

* **Iterative Partition**: `O(1)`
ทำงานแบบ In-place บนอาร์เรย์เดิม ไม่ต้องสร้างอาร์เรย์เพิ่ม
* **Sorting-Based**: `O(log n)` ถึง `O(n)`
ขึ้นอยู่กับอัลกอริทึมการเรียงลำดับภายในของภาษา (เช่น Dual-Pivot Quicksort หรือ Timsort)

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Iterative Partition**
* **ข้อดี**: ทำงานรวดเร็วมาก (`O(n)`) ประหยัดหน่วยความจำ
* **ข้อจำกัด**: ข้อมูลภายในแต่ละฝั่งจะไม่ถูกเรียงลำดับ (Unsorted Within Partition)


* **Sorting-Based**
* **ข้อดี**: ผลลัพธ์ที่ได้ถูกเรียงลำดับอย่างเป็นระเบียบสมบูรณ์
* **ข้อจำกัด**: ประมวลผลช้ากว่า (`O(n log n)`) ทำงานเกินความจำเป็นเนื่องจากโจทย์ต้องการเพียงแค่การแบ่งกลุ่ม



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* **Iterative Partition** เหมาะสมที่สุดสำหรับการใช้งานจริงตามโจทย์ข้อนี้ เนื่องจากตรงตามวัตถุประสงค์ของการแบ่งกลุ่ม (Partition) และใช้เวลาในการประมวลผลน้อยที่สุด (`O(n)`)

```

```
---

## ข้อที่ 6: การค้นหาคู่จำนวนที่มีผลรวมเท่ากับ k

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **วิธีที่ 1: Recursive Two-Pointer (`findPairRecursive`)**  
  สำหรับอาร์เรย์ที่เรียงลำดับแล้ว ใช้ตัวชี้ `left` (เริ่มต้นที่ดัชนีแรก) และ `right` (เริ่มต้นที่ดัชนีสุดท้าย) เปรียบเทียบผลรวม `A[left] + A[right]` กับค่า `k` หากผลรวมน้อยกว่า `k` ให้ขยับ `left` ไปทางขวา หากผลรวมมากกว่า `k` ให้ขยับ `right` ไปทางซ้าย โดยทำการเวียนเกิด (Recursion) ไปเรื่อยๆ จนกว่าจะพบคู่ที่ต้องการหรือตัวชี้ชนกัน
* **วิธีที่ 2: Binary Search (`findPairBinarySearch`)**  
  สำหรับอาร์เรย์ที่เรียงลำดับแล้ว วนลูปเลือกสมาชิกตัวตั้ง `A[i]` ทีละตัว แล้วใช้อัลกอริทึม Binary Search ค้นหาค่าส่วนเติมเต็ม (Complement) คือ `k - A[i]` ในช่วงดัชนีที่เหลือ `A[i+1...n-1]`

---

### 2. Pseudocode หรือผังขั้นตอนการทำงาน

```text
Algorithm findPairRecursive(A, k, left, right):
    if left >= right then return false
    sum = A[left] + A[right]
    if sum == k then return true
    if sum < k then return findPairRecursive(A, k, left + 1, right)
    else return findPairRecursive(A, k, left, right - 1)

Algorithm findPairBinarySearch(A, k):
    for i = 0 to A.length - 1 do
        complement = k - A[i]
        if binarySearch(A, i + 1, A.length - 1, complement) != -1 then
            return true
    return false
```

---

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
public class PairSumFinder {

    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        if (a == null || left >= right) return false;
        int sum = a[left] + a[right];
        if (sum == k) {
            System.out.println("Pair found: " + a[left] + " and " + a[right]);
            return true;
        }
        if (sum < k) return findPairRecursive(a, k, left + 1, right);
        else return findPairRecursive(a, k, left, right - 1);
    }

    public static boolean findPairBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) return false;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int complement = k - a[i];
            int index = binarySearchHelper(a, i + 1, n - 1, complement);
            if (index != -1) {
                System.out.println("Pair found: " + a[i] + " and " + a[index]);
                return true;
            }
        }
        return false;
    }

    private static int binarySearchHelper(int[] a, int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (a[mid] == target) return mid;
        else if (a[mid] < target) return binarySearchHelper(a, mid + 1, high, target);
        else return binarySearchHelper(a, low, mid - 1);
    }
}

```

---

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์

* **Input**: `A = [2, 4, 7, 11, 15, 20]` *(อาร์เรย์เรียงลำดับแล้ว)*, `k = 18`
* **Output**: `Pair found: 7 and 11` -> `true`

---

### 5. การวิเคราะห์ Time Complexity

* **Recursive Two-Pointer**: `O(n)`
ขยับตัวชี้ `left` หรือ `right` เข้าหากันทีละ 1 ตำแหน่ง ทำงานสูงสุด $n$ ครั้ง
* **Binary Search**: `O(n log n)`
วนลูปเลือกตัวตั้ง $n$ ครั้ง แต่ละครั้งใช้ Binary Search ค้นหาซึ่งใช้เวลา `O(log n)` รวมเป็น `O(n log n)`

---

### 6. การวิเคราะห์ Space Complexity

* **Recursive Two-Pointer**: `O(n)`
ใช้หน่วยความจำ Call Stack ตามความลึกสูงสุด $n$ ชั้นเมื่อเรียกเวียนเกิด
* **Binary Search**: `O(log n)`
ใช้พื้นที่ Call Stack ตามความลึกของการทำ Binary Search

---

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

* **Recursive Two-Pointer**
* **ข้อดี**: ทำงานได้รวดเร็วที่สุดเชิงเวลา (`O(n)`) โค้ดอ่านเข้าใจง่าย
* **ข้อจำกัด**: เสี่ยงเกิด `StackOverflowError` หากอาร์เรย์มีขนาดใหญ่มาก


* **Binary Search**
* **ข้อดี**: ใช้พื้นที่ Call Stack ต่ำกว่ามาก (`O(log n)`)
* **ข้อจำกัด**: ประมวลผลช้ากว่า (`O(n log n)`) เนื่องจากต้องทำค้นหาซ้ำซ้อนทุกรอบวงวน



---

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด

* **Two-Pointer** ให้ประสิทธิภาพเชิงเวลาดีที่สุด (`O(n)`) แต่สำหรับการใช้งานจริงในระบบ Production เพื่อป้องกันความเสี่ยงจาก Stack ล้น ควรแปลงแนวคิด Two-Pointer นี้ไปใช้รูปแบบ Iterative Loop แทน

```

```
