import http from "../http-common"; 

class EventLogService {
  getAllEventLogs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/eventLog/eventLogs`, searchDTO);
  }

  get(eventLogId) {
    return this.getRequest(`/eventLog/${eventLogId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/eventLog?field=${matchData}`, null);
  }

  addEventLog(data) {
    return http.post("/eventLog/addEventLog", data);
  }

  update(data) {
  	return http.post("/eventLog/updateEventLog", data);
  }
  
  uploadImage(data,eventLogId) {
  	return http.postForm("/eventLog/uploadImage/"+eventLogId, data);
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

export default new EventLogService();
