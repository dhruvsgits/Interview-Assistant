import React, { useState, useEffect } from "react";
import axios from "axios";

export default function InterviewChat({ candidate }) {
  const [session, setSession] = useState(null);
  const [answer, setAnswer] = useState("");

  useEffect(() => {
    if (candidate) {
      axios.post("/interview/start", candidate).then((res) => {
        setSession(res.data);
      });
    }
  }, [candidate]);

  const submitAnswer = async () => {
    const res = await axios.post(`/interview/${session.id}/submit`, { answer });
    setSession(res.data);
    setAnswer("");
  };

  if (!session) return <div>Loading Interview...</div>;

  if (session.completed) return <div>Interview completed! Score: {session.score}</div>;

  const question = session.questions[session.answers.length];

  return (
    <div>
      <p><b>Question:</b> {question}</p>
      <textarea value={answer} onChange={(e) => setAnswer(e.target.value)} />
      <button onClick={submitAnswer}>Submit</button>
      <p>Current Score: {session.score}</p>
    </div>
  );
}