import styles from "./page.module.css";

export default function Home() {
    return (
        <div className={styles.page}>
            Main hi
            <iframe
                src="http://localhost:3001" // Ссылка на game-slots
                width="100%"
                height="800px"
            />
        </div>
    );
}

