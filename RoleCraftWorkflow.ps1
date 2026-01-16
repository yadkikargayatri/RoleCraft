# -------------------------------
# RoleCraft End-to-End Workflow
# -------------------------------

# --- 1️⃣ Upload Resume ---
$resumePath = "C:\Users\rajat\Downloads\Gayatri Yadkikar_Software Engineer_Resume.pdf"

$resume = curl.exe -X POST "http://localhost:8080/api/resumes/upload" `
    -F ("file=@" + $resumePath)

$resumeObj = $resume | ConvertFrom-Json

Write-Host "`n--- Parsed Resume ---"
$resumeObj | ConvertTo-Json -Depth 10

# --- 2️⃣ Parse Job Description ---
$jdText = @"
Software Engineer
Responsibilities:
- Build scalable backend services
- Design REST APIs

Required:
Java, Spring Boot, AWS

Preferred:
Docker, React
"@

$jobDescription = curl.exe -X POST "http://localhost:8080/api/job-description/parse" `
    -H "Content-Type: application/json" `
    -d ("{`"rawText`": `"$jdText`"}")

$jdObj = $jobDescription | ConvertFrom-Json

Write-Host "`n--- Parsed Job Description ---"
$jdObj | ConvertTo-Json -Depth 10

# --- 3️⃣ Skill Matching ---
$skillMatchJson = @{
    resumeSkills       = $resumeObj.skills
    jobRequiredSkills  = $jdObj.requiredSkills
    jobPreferredSkills = $jdObj.preferredSkills
} | ConvertTo-Json -Compress

$skillMatch = curl.exe -X POST "http://localhost:8080/api/skills/match" `
    -H "Content-Type: application/json" `
    -d $skillMatchJson

$skillMatchObj = $skillMatch | ConvertFrom-Json

Write-Host "`n--- Skill Matching Result ---"
$skillMatchObj | ConvertTo-Json -Depth 10

# --- 4️⃣ Generate Tailored Resume ---
$tailorJson = @{
    resume        = $resumeObj
    jobDescription = $jdObj
} | ConvertTo-Json -Compress

$tailored = curl.exe -X POST "http://localhost:8080/api/resume/tailor" `
    -H "Content-Type: application/json" `
    -d $tailorJson

$tailoredObj = $tailored | ConvertFrom-Json

Write-Host "`n--- Tailored Resume ---"
$tailoredObj | ConvertTo-Json -Depth 10
