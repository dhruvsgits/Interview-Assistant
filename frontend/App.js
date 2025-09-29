import React, { useState } from "react";
import ResumeUpload from "./components/ResumeUpload";
import InterviewChat from "./components/InterviewChat";

function App() {
  const [candidate, setCandidate] = useState(null);

  return (
    <div>
      {!candidate ? <ResumeUpload onCandidateParsed={setCandidate} /> : <InterviewChat candidate={candidate} />}
    </div>
  );
}

export default App;