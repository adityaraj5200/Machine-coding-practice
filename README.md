In a **Machine Coding Round** (common in companies like Flipkart, Swiggy, Razorpay, Cleartrip, etc.), you're expected to implement a **working, modular, object-oriented system** (usually CLI-based) **within 1.5 to 3 hours**. Here's exactly what to expect:

---

### ✅ **Expectations**

### 1. **Functionality**

- Working code with core features (e.g., booking in cab system, moves in tic tac toe, parking in parking lot).
- All classes and methods logically structured.

### 2. **Object-Oriented Design**

- Use classes, interfaces, enums properly.
- Avoid procedural style (just main method + everything inside).

### 3. **Separation of Concerns**

- Avoid monolithic classes. Keep business logic, models, services, and UI separate.

### 4. **Modularity**

- Clean methods, small responsibilities.
- No God classes.

### 5. **Extendability**

- Design should be flexible for future additions (e.g., adding new vehicle type, or more features like `unpark()`).

### 6. **Code Quality**

- Meaningful class/variable/method names.
- Proper access modifiers.
- No hardcoded values.
- Exception handling where necessary.

### 7. **CLI I/O**

- Read and process commands like:
    
    ```
    park KA-01-HH-1234 CAR
    leave 2
    status
    ```
    

---

### 🧠 **Common Machine Coding Problems**

- Tic Tac Toe
- Snake & Ladder
- Parking Lot
- BookMyShow system (simple version)
- Elevator system
- Splitwise
- Chess (basic moves)
- In-memory database
- Logging Rate Limiter
- Vending Machine

---

### ❌ **What is *not* expected**

- GUI
- Database persistence
- Unit tests (unless explicitly asked)
- 100% feature completion (if time is short)

---

### 🛠️ Tips

- Think before coding: list out classes/interfaces first
- Write `README` if allowed, explaining assumptions
- Use enums and interfaces smartly
- Keep edge cases in mind
