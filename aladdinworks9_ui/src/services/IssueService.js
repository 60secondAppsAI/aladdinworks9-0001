import http from "../http-common"; 

class IssueService {
  getAllIssues(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/issue/issues`, searchDTO);
  }

  get(issueId) {
    return this.getRequest(`/issue/${issueId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/issue?field=${matchData}`, null);
  }

  addIssue(data) {
    return http.post("/issue/addIssue", data);
  }

  update(data) {
  	return http.post("/issue/updateIssue", data);
  }
  
  uploadImage(data,issueId) {
  	return http.postForm("/issue/uploadImage/"+issueId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new IssueService();
