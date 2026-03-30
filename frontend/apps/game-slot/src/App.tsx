import { useState } from "react";
import "./App.css";

function App() {
    const [count, setCount] = useState(0);

    return (
        <>
            Hello {count}
            <button onClick={() => setCount(count + 1)}>+1</button>
        </>
    );
}

export default App;

