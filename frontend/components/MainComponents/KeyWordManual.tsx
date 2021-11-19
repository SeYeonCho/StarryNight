import styles from '../../styles/ResultManual.module.scss';

const KeyWordManual = () => {
    
    return (
        <div className={styles.manualBox}>
            오늘의 인기 검색어 20개를 관련 뉴스기사와
            <br/>
            함께 인기순으로 정렬해 보여줍니다.
        </div>
    );

}

export default KeyWordManual;