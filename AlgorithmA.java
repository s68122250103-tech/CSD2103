package algorithms;

import java.util.Stack;
import models.Vehicle;

public class AlgorithmA {
    /*
     * Loop Invariant สำหรับ Algorithm A:
     * 1. Initialization: ก่อนเริ่ม Loop แรก รถทุกคันอยู่ใน var0 (Parking Stack) และ var2 (Temporary Stack) ยังว่างเปล่า
     * 2. Maintenance: เมื่อดึงรถออกจาก var0 ถ้ารถไม่ใช่เป้าหมาย จะถูกนำไปเก็บใน var2 ทำให้รถไม่สูญหาย และลำดับจะถูกกลับหัวชั่วคราว
     * 3. Termination: Loop จบลงเมื่อเจอรถเป้าหมาย หรือ var0 ว่างเปล่า หลังจากนั้น Loop ที่สองจะดึงรถจาก var2 กลับเข้า var0 ซึ่งจะทำให้รถกลับมาอยู่ในลำดับเดิมพอดี
     */
    public static boolean removeVehicleIterative(Stack<Vehicle> var0, String var1) {
        Stack<Vehicle> var2 = new Stack<>();
        boolean var3 = false;

        while(!var0.isEmpty()) {
            Vehicle var4 = var0.pop();
            if (var4.getLicensePlate().equals(var1)) {
                var3 = true;
                break;
            }
            var2.push(var4);
        }

        while(!var2.isEmpty()) {
            var0.push(var2.pop());
        }

        return var3;
    }
}