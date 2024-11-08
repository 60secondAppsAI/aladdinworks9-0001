import http from "../http-common"; 

class IssueResolutionService {
  getAllIssueResolutions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/issueResolution/issueResolutions`, searchDTO);
  }

  get(issueResolutionId) {
    return this.getRequest(`/issueResolution/${issueResolutionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/issueResolution?field=${matchData}`, null);
  }

  addIssueResolution(data) {
    return http.post("/issueResolution/addIssueResolution", data);
  }

  update(data) {
  	return http.post("/issueResolution/updateIssueResolution", data);
  }
  
  uploadImage(data,issueResolutionId) {
  	return http.postForm("/issueResolution/uploadImage/"+issueResolutionId, data);
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

export default new IssueResolutionService();
