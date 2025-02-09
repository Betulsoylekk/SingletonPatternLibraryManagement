###### **Library Management System Overview**


This Library Management System is designed to simulate a real-world library, where books are added, updated, lent, and retrieved by members. The system uses the Singleton Design Pattern to ensure consistency and prevent the creation of multiple instances of the library. By employing this approach, we ensure that all users interact with the same shared collection of books, providing a consistent state across the application.

Design Decisions
The system has been designed to simulate real-world application scenarios while maintaining simplicity and clarity. The following design decisions have been made to reflect common real-world practices:

Singleton Pattern:
The LibraryImpl class is designed as a singleton, meaning only one instance of the library exists throughout the lifetime of the application. This ensures that all operations (such as adding or removing books) are performed on the same dataset, avoiding inconsistencies that could arise from multiple instances.

Why Singleton? The Singleton Pattern ensures that the library is shared across all users and provides a single point of interaction with the book collection. This is crucial for data consistency and prevents the issues that could arise from managing multiple instances of the library.
Thread Safety:
The LibraryImpl class handles concurrent access to the book list using synchronized blocks and locks, ensuring that operations like lending a book and updating a book are done thread-safely. This prevents race conditions in environments with multiple users or threads.

Real-World Simulation:
The project simulates real-world functionality:

Book lending: Only available books can be lent, and the availability is updated when a book is lent or retrieved.
Updating and Removing Books: Books can be updated with new details, and books can be removed if they are not required anymore.

Simple Exception Handling:
Exception handling has been added to handle cases where actions cannot be completed (e.g., lending an unavailable book, retrieving a book not borrowed by a member, etc.).




**Resetting Static State in Unit Tests Issue**

Since LibraryImpl uses the Singleton pattern with static variables (e.g., bookList), data persists between tests, causing potential inconsistencies and test failures.

_**Solution**_

We added a static clearBookList() method to reset the bookList before each test, ensuring a clean state for each test case.

_**Approach**_

Clear Static State: The clearBookList() method clears the static bookList.

Test Setup: In the @Before method, we call clearBookList() to ensure tests run with a fresh state.


`@Before
public void setUp() {
library = LibraryImpl.initializeLibrary();  // Initialize Singleton
LibraryImpl.clearBookList();  // Reset bookList
}`

_**Conclusion**_

This approach ensures that each test is independent and unaffected by the static state, making tests more reliable and repeatable.



