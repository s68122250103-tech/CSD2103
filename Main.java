import java.util.Scanner;
import java.util.Stack;

class Vehicle {
    private String licensePlate;
    private String ownerName;
    private String entryTime;
    private String vehicleType;

    public Vehicle(String licensePlate, String ownerName, String entryTime, String vehicleType) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return "[" + licensePlate + "]";
    }
}

public class Main {

    // ==========================================
    // ALGORITHM A (Iterative)
    // ==========================================
    public static boolean removeVehicleIterative(Stack<Vehicle> var0, String var1) {
        return removeVehicleIterative(var0, var1, new int[1]);
    }

    public static boolean removeVehicleIterative(Stack<Vehicle> var0, String var1, int[] ops) {
        Stack<Vehicle> var2 = new Stack<>();
        boolean var3 = false;

        while(!var0.isEmpty()) {
            ops[0]++; 
            Vehicle var4 = var0.pop();
            if (var4.getLicensePlate().equals(var1)) {
                var3 = true;
                break;
            }
            var2.push(var4);
        }

        while(!var2.isEmpty()) {
            ops[0]++; 
            var0.push(var2.pop());
        }

        return var3;
    }

    // ==========================================
    // ALGORITHM B (Recursive)
    // ==========================================
    public static boolean removeVehicleRecursive(Stack<Vehicle> stack, String targetPlate) {
        return removeVehicleRecursive(stack, targetPlate, new int[1]);
    }

    public static boolean removeVehicleRecursive(Stack<Vehicle> stack, String targetPlate, int[] ops) {
        ops[0]++; 
        if (stack.isEmpty()) {
            return false;
        }

        Vehicle currentVehicle = stack.pop();

        if (currentVehicle.getLicensePlate().equals(targetPlate)) {
            return true;
        }

        boolean isFound = removeVehicleRecursive(stack, targetPlate, ops);
        stack.push(currentVehicle);
        ops[0]++; 
        
        return isFound;
    }

    // ==========================================
    // Mock Parking Lot
    // ==========================================
    private static Stack<Vehicle> createMockParkingLot() {
        Stack<Vehicle> lot = new Stack<>();
        lot.push(new Vehicle("AA1111", "Alice", "08:00", "Car"));   
        lot.push(new Vehicle("BB2222", "Bob", "08:15", "Car"));
        lot.push(new Vehicle("CC3333", "Charlie", "08:30", "SUV"));
        lot.push(new Vehicle("DD4444", "David", "08:45", "Truck")); 
        return lot;
    }

    // ==========================================
    // Test Runner
    // ==========================================
    private static void runTestCase(String caseName, Stack<Vehicle> lotForA, Stack<Vehicle> lotForB, String target) {
        System.out.println("\n>> " + caseName);
        System.out.println("Target: " + target);
        
        int[] opsA = new int[1];
        long startA = System.nanoTime();
        boolean resA = removeVehicleIterative(lotForA, target, opsA);
        long timeA = System.nanoTime() - startA;

        int[] opsB = new int[1];
        long startB = System.nanoTime();
        boolean resB = removeVehicleRecursive(lotForB, target, opsB);
        long timeB = System.nanoTime() - startB;

        System.out.println("[Algorithm A] Result: " + resA + " | Operations: " + opsA[0] + " | Time: " + timeA + " ns");
        System.out.println("[Algorithm B] Result: " + resB + " | Operations: " + opsB[0] + " | Time: " + timeB + " ns");
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Vehicle> mainParkingLot = createMockParkingLot();
        
        while (true) {
            System.out.println("\n=== 🚗 Smart Parking System 🚗 ===");
            System.out.println(" 1. Normal Case");
            System.out.println(" 2. Best Case");
            System.out.println(" 3. Worst Case");
            System.out.println(" 4. Empty Stack");
            System.out.println(" 5. Algorithm A");
            System.out.println(" 6. Algorithm B");
            System.out.println(" 7. Algorithm A + B");
            System.out.println(" 0. Exit");
            System.out.print("Select menu (0-7): ");
            
            String choice = scanner.nextLine();
            
            if (!choice.matches("[0-7]")) {
                System.out.println("\n[!] Invalid selection. Please enter 0-7.");
                continue;
            }

            int menu = Integer.parseInt(choice);

            if (menu == 0) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            String target = "";
            // เมนู 1-4 และ 7 ใช้ลานจอดจำลอง (Mock) เพื่อให้ข้อมูลรถรีเซ็ตใหม่ทุกครั้ง
            if (menu >= 1 && menu <= 4 || menu == 7) {
                System.out.println("\nMock parking lot: " + createMockParkingLot());
                System.out.print("Please enter the target license plate: ");
                target = scanner.nextLine();
            } 
            // เมนู 5-6 ใช้ลานจอดหลัก (ทำแล้วรถหายจริง)
            else if (menu == 5 || menu == 6) {
                System.out.println("\nCurrent Lot (Before): " + mainParkingLot);
                System.out.print("Please enter the target license plate: ");
                target = scanner.nextLine();
            }

            switch (menu) {
                case 1:
                    runTestCase("Normal Case", createMockParkingLot(), createMockParkingLot(), target);
                    break;
                case 2:
                    runTestCase("Best Case", createMockParkingLot(), createMockParkingLot(), target);
                    break;
                case 3:
                    runTestCase("Worst Case", createMockParkingLot(), createMockParkingLot(), target);
                    break;
                case 4:
                    System.out.println("\n(Testing with an empty stack)");
                    runTestCase("Empty Stack", new Stack<>(), new Stack<>(), target);
                    break;
                case 5:
                    boolean foundA = removeVehicleIterative(mainParkingLot, target);
                    System.out.println("Result Alg A: " + (foundA ? "Success" : "Not found"));
                    System.out.println("Current Lot (After): " + mainParkingLot);
                    break;
                case 6:
                    boolean foundB = removeVehicleRecursive(mainParkingLot, target);
                    System.out.println("Result Alg B: " + (foundB ? "Success" : "Not found"));
                    System.out.println("Current Lot (After): " + mainParkingLot);
                    break;
                case 7:
                    // ใช้ลานจอดจำลอง เพื่อให้โชว์รถครบๆ เวลาเปรียบเทียบ
                    Stack<Vehicle> lotForA = createMockParkingLot();
                    Stack<Vehicle> lotForB = createMockParkingLot();
                    
                    boolean resA = removeVehicleIterative(lotForA, target);
                    System.out.println("Result Alg A: " + (resA ? "Success" : "Not found") + " | Lot: " + lotForA);
                    
                    boolean resB = removeVehicleRecursive(lotForB, target);
                    System.out.println("Result Alg B: " + (resB ? "Success" : "Not found") + " | Lot: " + lotForB);
                    break;
            }
        }
        scanner.close();
    }
}