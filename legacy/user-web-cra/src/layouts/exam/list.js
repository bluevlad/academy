import React, { Component } from 'react';
import { Row, Col, Card, Table, Pagination, Button } from 'react-bootstrap';
import { BASE_API } from "../../config/constant";
import { fetchExamData } from './data';
import { useNavigate } from "react-router-dom";
import superagent from 'superagent';
import EdExam from './reExam';


export function withRouter(Component) {
  return function WithRouterComponent(props) {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    return <Component {...props} token={token} userId={userId} navigate={navigate} />;
  };
}

class List extends Component {

  constructor (props) {
    super();
    this.state = {
      userId: props.userId || "",
      token: props.token || "",
      examList: [], // 시험정보 데이터
      paginationInfo: {}, // 페이지네이션 정보
      loaded: false, // 로딩 상태
      activePage: 1, // 현재 페이지
      showReExam: false, // 재응시 팝업
      reExamId: null,    // 재응시할 시험 ID
    };
  }

  // 🔹 비동기 데이터 호출을 `async/await`으로 변경
  async componentDidMount() {
    await this.loadExamData(1);
  }

  // 🔹 특정 페이지 데이터 로드
  loadExamData = async (page) => {
    try {
      const { examList, paginationInfo } = await fetchExamData(page);

      this.setState({
        examList,
        paginationInfo,
        activePage: page,
        loaded: true,
      });
    } catch (error) {
      console.error("데이터 로드 오류:", error);
    }
  };

  // 날짜 포맷 함수 수정: 202301010800 형태 지원
  formatDateTime(dateStr) {
    if (!dateStr) return '';
    // 12자리(YYYYMMDDHHmm) 형태라면 직접 파싱
    if (/^\d{12}$/.test(dateStr)) {
      const yyyy = dateStr.substring(0, 4);
      const mm = dateStr.substring(4, 6);
      const dd = dateStr.substring(6, 8);
      const hh = dateStr.substring(8, 10);
      const min = dateStr.substring(10, 12);
      return `${yyyy}/${mm}/${dd} ${hh}:${min}`;
    }
    // Date 객체로 파싱 시도 (기존 방식)
    const date = new Date(dateStr);
    if (isNaN(date.getTime())) return dateStr; // 파싱 실패 시 원본 반환
    const yyyy = date.getFullYear();
    const mm = String(date.getMonth() + 1).padStart(2, '0');
    const dd = String(date.getDate()).padStart(2, '0');
    const hh = String(date.getHours()).padStart(2, '0');
    const min = String(date.getMinutes()).padStart(2, '0');
    return `${yyyy}/${mm}/${dd} ${hh}:${min}`;
  }

  // 🔹 페이지 변경 핸들러
  handlePageChange = (page) => {
    if (page >= 1 && page <= this.state.paginationInfo.totalPageCount) {
      this.loadExamData(page);
    }
  };

  vwExam = (examId) => {
    this.props.navigate('/exam/view?id='+examId);
  };
    
  // 🔹 글쓰기 페이지 이동 함수
  onExam = async (id) => {
  
    try {
      const res = await superagent
        .post(`${BASE_API}/exam/getExamView`)
        .type("form")
        .send({ examId: id });

      if (res.body.retMsg === 'Y') {
        this.props.navigate(`/exam/view?id=${id}`);
      } else {
        alert("시험 응시 여부 확인 실패: " + res.body.retMsg);
        this.props.navigate('/exam/list');
      }
    } catch (err) {
      console.error("시험 조회 오류:", err);
      alert("시험 조회 중 오류가 발생했습니다.");
    }
  };

  handleCloseReExam = () => {
    this.setState({ showReExam: false, reExamId: null });
  };

  render() {
    const { examList, paginationInfo, loaded, activePage, showReExam, reExamId, userId, token } = this.state;

    return (
      <React.Fragment>
        <Row>
          <Col>
            <Card>
              <Card.Header>
                <Card.Title as="h5">시험정보</Card.Title>
              </Card.Header>
              <Card.Body>
                {loaded ? (
                  <Table responsive hover>
                    <thead>
                      <tr>
                        <th>#</th>
                        <th>시험명</th>
                        <th>시험기간</th>
                        <th>시험시간</th>
                        <th>응시여부</th>
                      </tr>
                    </thead>
                    <tbody>
                      {examList.map((item, index) => (
                        <tr key={item.exam_id} onClick={() => this.vwExam(item.exam_id)} style={{ cursor: "pointer" }}>
                          <th scope="row">{paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - index}</th>
                          <td>{item.exam_nm}</td>
                          <td>{this.formatDateTime(item.exam_open)} ~ {this.formatDateTime(item.exam_end)}</td>
                          <td>{item.exam_period}</td>
                          <td>
                            {item.user_id === this.state.userId ? (
                              <>
                                <Button variant="info" onClick={() => this.reExam(item.exam_id)}>재응시</Button>
                              </>
                            ) : (
                              <>
                                <Button variant="info" onClick={() => this.onExam(item.exam_id)}>응시</Button>
                              </>
                            )}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  <p>로딩 중...</p>
                )}

                {/* 페이지네이션 */}
                <Card>
                <div className="d-flex justify-content-center">
                    <Pagination>
                      <Pagination.First onClick={() => this.handlePageChange(1)} />
                      <Pagination.Prev
                        onClick={() => this.handlePageChange(activePage - 1)}
                        disabled={activePage === 1}
                      />

                      {[...Array(paginationInfo.totalPageCount || 1)].map((_, i) => (
                        <Pagination.Item
                          key={i + 1}
                          active={i + 1 === activePage}
                          onClick={() => this.handlePageChange(i + 1)}
                        >
                          {i + 1}
                        </Pagination.Item>
                      ))}

                      <Pagination.Next
                        onClick={() => this.handlePageChange(activePage + 1)}
                        disabled={activePage === paginationInfo.totalPageCount}
                      />
                      <Pagination.Last
                        onClick={() => this.handlePageChange(paginationInfo.totalPageCount)}
                      />
                    </Pagination>
                  </div>
                </Card>
              </Card.Body>
            </Card>
          </Col>
        </Row>
        {/* 재응시 팝업 */}
        {showReExam && (
          <EdExam
            show={showReExam}
            onClose={this.handleCloseReExam}
            examId={reExamId}
            userId={userId}
            token={token}
          />
        )}
      </React.Fragment>
    );
  }
}

export default withRouter(List);
