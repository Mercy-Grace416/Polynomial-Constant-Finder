# Polynomial-Constant-Finder
Problem Overview:-
This project computes the constant term (c) of a polynomial using its roots.
The roots are provided in a JSON file, where each root value is encoded in a
specific number base (binary, decimal, etc.).

The program:-
- Reads input from a JSON file
- Decodes each root from its given base to decimal
- Uses polynomial root properties
- Calculates the constant term `c`

Mathematical Logic:-
If a polynomial of degree `m` has roots:
r₁, r₂, ..., rₘ
Then the constant term is calculated as:
c = (-1)ᵐ × (r₁ × r₂ × ... × rₘ)
Only the minimum required roots(`k - 1`) are used, where `k` is provided in
the input file.
RESULT
The constant term c = 6
The constant term c = -10
=== Starting Polynomial Constant Finder ===

Step 1: Reading input.json file...
 File loaded successfully!

Step 2: Understanding the requirements...
  - Total roots provided: 4
  - Minimum roots needed: 3
  - Polynomial degree: 2

Step 3: Decoding all roots from their bases...
  Root 1: '2' in base-10 = 2 (decimal)
  Root 2: '11' in base-2 = 3 (decimal)
  Root 3: '7' in base-8 = 7 (decimal)
  Root 4: 'A' in base-16 = 10 (decimal)
All roots decoded!

Step 4: Selecting roots for calculation...
  Using first 2 roots for polynomial of degree 2
  Selected root 1: 2
  Selected root 2: 3

Step 5: Calculating product of roots...
  Current product: 2
  Current product: 6
  Product calculated: 6
  For test case 1:6(even)
  For test case 2:-10(odd)
  

