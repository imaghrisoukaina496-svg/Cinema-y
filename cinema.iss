
#define MyAppName "Cinema App"
#define MyAppVersion "1.0"
#define MyAppPublisher "Me"
#define MyAppExeBat "CinemaApp.bat"

[Setup]
AppId={{A1B2C3D4-1111-2222-3333-444455556666}}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={commonpf}\{#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=output
OutputBaseFilename=Setup_CinemaApp
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "french"; MessagesFile: "compiler:Languages\French.isl"

[Tasks]
Name: "desktopicon"; Description: "Créer un raccourci sur le bureau"; Flags: unchecked

[Files]
; --- JAR principal ---
Source: "C:\Users\Original Computer\Documents\NetBeansProjects\Cenima\dist\Cenima.jar"; DestDir: "{app}"; Flags: ignoreversion

; --- Librairies (dist\lib) ---
Source: "C:\Users\Original Computer\Documents\NetBeansProjects\Cenima\dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs

; --- Fichiers externes (décommente et adapte les chemins si besoin) ---
; Exemple images :
; Source: "C:\Users\Original Computer\Documents\NetBeansProjects\Cenima\images\*"; DestDir: "{app}\images"; Flags: ignoreversion recursesubdirs createallsubdirs
; Exemple base de données / data :
; Source: "C:\Users\Original Computer\Documents\NetBeansProjects\Cenima\data\*"; DestDir: "{app}\data"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeBat}"; WorkingDir: "{app}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeBat}"; WorkingDir: "{app}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeBat}"; Description: "Lancer {#MyAppName}"; Flags: nowait postinstall skipifsilent

[Code]
procedure CurStepChanged(CurStep: TSetupStep);
var
  BatPath, Content: string;
begin
  if CurStep = ssPostInstall then
  begin
    BatPath := ExpandConstant('{app}\{#MyAppExeBat}');

    Content :=
      '@echo off' + #13#10 +
      'cd /d "%~dp0"' + #13#10 +
      'java -jar "Cenima.jar"' + #13#10;

    SaveStringToFile(BatPath, Content, False);
  end;
end;
