
$arcadiaTables = @{
    "1" = @{
        "table" = "habitats";
        "execute" = $true;
        "image" = $true
    };
    "2" = @{
        "table" = "races";
        "execute" = $true;
        "image" = $false
    };
    "3" = @{
        "table" = "animals";
        "execute" = $true;
        "image" = $true
    };
    "4" = @{
        "table" = "users";
        "execute" = $true;
        "image" = $false
    };
    "5" = @{
        "table" = "veterinaryreport";
        "execute" = $true;
        "image" = $false
    };
    "6" = @{
        "table" = "hours";
        "execute" = $true;
        "image" = $false
    };
    "7" = @{
        "table" = "services";
        "execute" = $true;
        "image" = $false
    };
    "8" = @{
        "table" = "views";
        "execute" = $true;
        "image" = $false
    };
}

$path = ".\mocks\"

$collection = Get-Content "${path}arcadia_init.postman_collection.json" | Convertfrom-Json 

foreach ($key in $arcadiaTables.Keys | Sort-Object) {

    $arcadiaTable = $arcadiaTables.$key

    $table = $arcadiaTable.table

    if ($arcadiaTable.execute) {

        $folder = "step ${key} ${table}"
        "=====================================> ${folder}"

        if ($arcadiaTable.image) {

            $items = $collection.item

            $entries = @()

            foreach ($item in $collection.item) {
                "====> $folder $($item.name)"
                if ("$($item.name)" -eq "$folder") { 
                    $entries = $item[0].item.request.body.formdata 
                    "ooooooooooooo"
                }
            }

            $j = $index = -1;
            
            $entries | ForEach-Object {$j++; if ($_.src) {$index = $j}}

            $file = $entries[$index].src

            $filename = ($file -split '\/')[-1]
            
            $entries[$index].src = $file -replace ($filename, "{{imageFile}}")
            
        }

        $result = Set-Content -Pass "${path}arcadia.json" -Force -Value (ConvertTo-Json $collection -Depth 20)
        
        newman run "${path}arcadia.json" `
            -e ".\mocks\ecf-arcadia-back_env.json" `
            -d ".\mocks\${table}.json" `
            --folder "${folder}" `
            --verbose

    }
    
}
