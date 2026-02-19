import { useState, useEffect } from "react";
import axios from "axios";

export default function BoardList() {

  const [list, setList] = useState([]);
  const [searchType, setSearchType] = useState("subject");
  const [searchKeyword, setSearchKeyword] = useState("");
  const [page, setPage] = useState(1);

  // 게시글 가져오기
  const getBoardList = () => {

    axios.get("/api/board/list", {
      params: {
        searchType: searchType,
        searchKeyword: searchKeyword,
        page: page,
        pageSize: 5
      }
    })
    .then((res) => {
      setList(res.data.list);   // Spring에서 보낸 list
    })
    .catch((err) => {
      console.log(err);
    });
  };

  // 페이지 바뀌면 자동 실행
  useEffect(() => {
    getBoardList();
  }, [page]);

  // 검색 버튼 클릭
  const handleSearch = () => {
    setPage(1);
    getBoardList();
  };

  return (
    <div>
      <h2>게시판</h2>

      {/* 검색 영역 */}
      <select
        value={searchType}
        onChange={(e) => setSearchType(e.target.value)}
      >
        <option value="subject">제목</option>
        <option value="content">내용</option>
      </select>

      <input
        type="text"
        value={searchKeyword}
        onChange={(e) => setSearchKeyword(e.target.value)}
      />

      <button onClick={handleSearch}>검색</button>

      <hr />

      {/* 게시글 목록 */}
      {list.map((item) => (
        <div key={item.num}>
          <h4>{item.subject}</h4>
          <p>작성자 : {item.writer}</p>
          <p>조회수 : {item.readcount}</p>
        </div>
      ))}

      <hr />

      {/* 간단 페이징 */}
      <button onClick={() => setPage(page - 1)}>이전</button>
      <span> {page} </span>
      <button onClick={() => setPage(page + 1)}>다음</button>
    </div>
  );
}
