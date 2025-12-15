import java.io.*;
import java.util.*;

public class PolynomialConstantFinder {

    public static void main(String[] args) {
        
        System.out.println("=== Starting Polynomial Constant Finder ===\n");
        
        try {
            // Step 1: Read the JSON file (like opening a book)
            System.out.println("Step 1: Reading input.json file...");
            String jsonContent = readFile("input.json");
            System.out.println("✓ File loaded successfully!\n");
            
            // Step 2: Parse JSON manually (understanding the requirements)
            System.out.println("Step 2: Understanding the requirements...");
            
            // Extract n and k values
            int n = extractNumber(jsonContent, "\"n\"");
            int k = extractNumber(jsonContent, "\"k\"");
            
            System.out.println("  - Total roots provided: " + n);
            System.out.println("  - Minimum roots needed: " + k);
            System.out.println("  - Polynomial degree: " + (k - 1) + "\n");
            
            // Step 3: Decode each root (converting to normal numbers)
            System.out.println("Step 3: Decoding all roots from their bases...");
            List<Long> decodedRoots = new ArrayList<>();
            
            // Go through each numbered key (1, 2, 3, etc.)
            for (int i = 1; i <= n; i++) {
                String key = "\"" + i + "\"";
                
                if (jsonContent.contains(key + ":")) {
                    // Extract base and value for this root
                    String base = extractValue(jsonContent, key, "base");
                    String value = extractValue(jsonContent, key, "value");
                    
                    if (base != null && value != null) {
                        int baseNum = Integer.parseInt(base);
                        
                        // Convert from given base to decimal (like translating)
                        long decimalValue = convertToDecimal(value, baseNum);
                        decodedRoots.add(decimalValue);
                        
                        System.out.println("  Root " + i + ": '" + value + "' in base-" + baseNum + " = " + decimalValue + " (decimal)");
                    }
                }
            }
            System.out.println("✓ All roots decoded!\n");
            
            // Step 4: Select the roots we need (picking the right ones)
            System.out.println("Step 4: Selecting roots for calculation...");
            int degree = k - 1;
            System.out.println("  Using first " + degree + " roots for polynomial of degree " + degree);
            
            List<Long> selectedRoots = new ArrayList<>();
            for (int i = 0; i < degree && i < decodedRoots.size(); i++) {
                selectedRoots.add(decodedRoots.get(i));
                System.out.println("  Selected root " + (i + 1) + ": " + decodedRoots.get(i));
            }
            System.out.println();
            
            // Step 5: Multiply all selected roots (doing the math)
            System.out.println("Step 5: Calculating product of roots...");
            long product = 1;
            for (long root : selectedRoots) {
                product = product * root;
                System.out.println("  Current product: " + product);
            }
            System.out.println("✓ Product calculated: " + product + "\n");
            
            // Step 6: Apply sign based on degree (final touch)
            System.out.println("Step 6: Applying sign based on degree...");
            long constantC;
            if (degree % 2 == 0) {
                // Even degree: positive
                constantC = product;
                System.out.println("  Degree is EVEN (" + degree + ") → constant is POSITIVE");
            } else {
                // Odd degree: negative
                constantC = -product;
                System.out.println("  Degree is ODD (" + degree + ") → constant is NEGATIVE");
            }
            System.out.println();
            
            // Step 7: Show final answer (celebration!)
            System.out.println("=== RESULT ===");
            System.out.println("The constant term c = " + constantC);
            System.out.println("==============\n");
            
        } catch (Exception e) {
            System.out.println("❌ Oops! Something went wrong:");
            System.out.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Read entire file content as a string
     */
    private static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
    
    /**
     * Extract a number value from JSON string
     * Example: "n": 4 → returns 4
     */
    private static int extractNumber(String json, String key) {
        String pattern = key + "\\s*:\\s*(\\d+)";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }
    
    /**
     * Extract a string value from JSON for a specific root
     * Example: "1": { "base": "10", ... } → returns "10"
     */
    private static String extractValue(String json, String rootKey, String fieldName) {
        // Find the section for this root
        int rootIndex = json.indexOf(rootKey + ":");
        if (rootIndex == -1) return null;
        
        // Find the field within this root section
        int fieldIndex = json.indexOf("\"" + fieldName + "\"", rootIndex);
        if (fieldIndex == -1 || fieldIndex > json.indexOf("}", rootIndex)) return null;
        
        // Extract the value
        int colonIndex = json.indexOf(":", fieldIndex);
        int startQuote = json.indexOf("\"", colonIndex);
        int endQuote = json.indexOf("\"", startQuote + 1);
        
        if (startQuote != -1 && endQuote != -1) {
            return json.substring(startQuote + 1, endQuote);
        }
        return null;
    }
    
    /**
     * Converts a number from any base to decimal
     * Like translating: binary "111" → decimal 7
     */
    private static long convertToDecimal(String value, int base) {
        // Java has a built-in function for this!
        return Long.parseLong(value, base);
    }
}
## Sample Outputs
Test Case 1
Step 1: Reading input.json file...
File loaded successfully!
Step 2: Understanding the requirements...
Total roots provided: 4
Minimum roots needed: 3
Polynomial degree: 2
Step 3: Decoding all roots from their bases...
Root 1: '2' in base-10 = 2
Root 2: '11' in base-2 = 3
Root 3: '7' in base-8 = 7
Root 4: 'A' in base-16 = 10
All roots decoded!
Step 4: Selecting roots for calculation...
Using first 2 roots for polynomial of degree 2
Selected root 1: 2
Selected root 2: 3
Step 5: Calculating product of roots...
Current product: 2
Current product: 6
Degree is EVEN (2) → constant is POSITIVE
The constant term c = 6  

### Test Case 2
Step 1: Reading input.json file...
File loaded successfully!
Step 2: Understanding the requirements...
Total roots provided: 3
Minimum roots needed: 4
Polynomial degree: 3
Step 3: Decoding all roots from their bases...
Root 1: '1' in base-10 = 1
Root 2: '10' in base-2 = 2
Root 3: '5' in base-10 = 5
All roots decoded!
Step 4: Selecting roots for calculation...
Using first 3 roots for polynomial of degree 3
Selected root 1: 1
Selected root 2: 2
Selected root 3: 5
Step 5: Calculating product of roots...
Current product: 1
Current product: 2
Current product: 10
Degree is ODD (3) → constant is NEGATIVE
The constant term c = -10




