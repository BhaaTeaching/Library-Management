import './App.css';
import AddBook from "./Components/AddBook/AddBook";
import GetAllBooks from "./Components/GetAllBooks/GetAllBooks";
import LibraryMainLayout from "./Components/MainPage/LibraryMainLayout";
import { BrowserRouter as Router} from "react-router-dom";
function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Router>
                {/*<AddBook/>*/}
                <LibraryMainLayout/>
                </Router>
            </header>
        </div>
    );
}

export default App;
