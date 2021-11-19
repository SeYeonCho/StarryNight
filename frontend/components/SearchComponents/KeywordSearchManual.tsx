import styles from '../../styles/ResultManual.module.scss';

const KeywordSearchManual = () => {
    
    return (
        <div className={styles.manualBox}>
            10글자 이상 단어를 검색하면,
            <br/>
            원하는 결과를 얻지 못할 수 있습니다.
        </div>
    );

}

export default KeywordSearchManual;