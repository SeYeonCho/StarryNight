import styles from '../../styles/ResultManual.module.scss';

const ClickRateResultManual = () => {
    
    return (
        <div className={styles.manualBox}>
            월간 평균 PC와 모바일 클릭률
            <br/>
            (클릭수 / 노출수 = 클릭률)
        </div>
    );

}

export default ClickRateResultManual;